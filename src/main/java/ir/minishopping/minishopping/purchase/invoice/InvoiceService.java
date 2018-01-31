package ir.minishopping.minishopping.purchase.invoice;

import com.querydsl.jpa.impl.JPAQueryFactory;
import ir.minishopping.minishopping.common.CodeGenerator;
import ir.minishopping.minishopping.person.customer.Customer;
import ir.minishopping.minishopping.person.customer.CustomerRepository;
import ir.minishopping.minishopping.purchase.InvoiceDTO;
import ir.minishopping.minishopping.purchase.InvoiceStatus;
import ir.minishopping.minishopping.purchase.product.Product;
import ir.minishopping.minishopping.purchase.product.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static ir.minishopping.minishopping.purchase.InvoiceStatus.CREATED;

@Slf4j
@Transactional
@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private InvoiceRowService invoiceRowService;

    //about pageable
    public Page<Invoice> getAllInvoices(Pageable pageable) {
        return invoiceRepository.findAll(pageable);
    }

    public Invoice getInvoice(String id) {
        return invoiceRepository.findOne(id);
    }

    public Invoice trackInvoice(String trackingNo) {
        return invoiceRepository.findByTrackingNo(trackingNo);
    }

    @PersistenceContext
    private EntityManager entityManager;

    public InvoiceCostDTO getInvoicesCosts() {

        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        QInvoice invoice = QInvoice.invoice;
        QInvoiceRow invoiceRow = QInvoiceRow.invoiceRow;

        InvoiceCostDTO invoiceCostDTO = new InvoiceCostDTO();
        invoiceCostDTO.setTotalCreated(jpaQueryFactory.selectFrom(invoice).where(invoice.enable.eq(true)).fetchCount());
        invoiceCostDTO.setTotalAmount(jpaQueryFactory.select(invoice.totalPrice.sum()).from(invoice).where(invoice.enable.isTrue()).fetchOne());
        invoiceCostDTO.setTotalSell(jpaQueryFactory.select(invoiceRow.countRow.sum()).from(invoiceRow).where(invoiceRow.enable.isTrue()).fetchOne());

        return invoiceCostDTO;
    }

    public String createInvoice(List<InvoiceDTO> invoiceDTOs, String id) {

        Customer customer = customerRepository.findOne(id);

        List<InvoiceRow> invoiceRowList = new ArrayList<>();//log.info(" >invoiceRowList {}", invoiceRowList);

        Invoice invoice = new Invoice();
        invoice.setCustomer(customer);
        invoice.setEnable(true);

        invoice.setStatus(CREATED);

        CodeGenerator codeGenerator = new CodeGenerator();
        String trackingNo = codeGenerator.randomUUID(10);

        invoice.setTrackingNo(trackingNo);

        invoiceRepository.save(invoice);

        BigDecimal totalPrice = BigDecimal.valueOf(0);

        for (InvoiceDTO invoiceDTO : invoiceDTOs) {

            InvoiceRow invoiceRow = new InvoiceRow();

            if (productRepository.findOne(invoiceDTO.getProductId()) == null) {
                throw new InvoiceException("Not enough storage of this product is available.");
            } else {
                Product product = productRepository.findOne(invoiceDTO.getProductId());

                int quantity = (invoiceDTO.getProductQuantity() != null) ? invoiceDTO.getProductQuantity() : 1;

                if (product.getExistQuantity() < quantity) {
                    throw new InvoiceException("Not enough storage of this product is available.");
                } else if (product.getPrice() != null) {

                    totalPrice = totalPrice.add(BigDecimal.valueOf(quantity).multiply(product.getPrice()));

                    invoiceRow.setCountRow(quantity);
                    invoiceRow.setProduct(product);
                    invoiceRow.setEnable(true);
                    invoiceRow.setInvoice(invoice);

                    invoiceRowService.save(invoiceRow);
                    invoiceRowList.add(invoiceRow);

                    product.setExistQuantity(product.getExistQuantity() - quantity);
                    productRepository.save(product);
                }
            }

        }

        invoice.setInvoiceRows(invoiceRowList);
        invoice.setTotalPrice(totalPrice);

        invoiceRepository.save(invoice);

        return " Thanks for your purchase, tracking number : " + trackingNo;
    }

    public void changeInvoiceState(String id, InvoiceStatus state) {

        if (invoiceRepository.findOne(id) == null) {
            throw new InvoiceException("id not found!");
        } else {
            Invoice invoice = invoiceRepository.findOne(id);
            if (state == InvoiceStatus.CREATED) {
                throw new InvoiceException("You are not allowed to change the state of invoice to CREATED state.");
            } else {
                invoice.setStatus(state);
            }

        }
    }

    public void updateInvoice(Invoice invoice, String id) {

        Invoice invoice_DB = invoiceRepository.findOne(id);

        if (invoice.getEnable() != null) {
            invoice_DB.setEnable(invoice.getEnable());
            log.info("******************"+invoice.getEnable());
            for (InvoiceRow invoiceRow : invoice.getInvoiceRows()) {
                invoiceRow.setEnable(invoice.getEnable());
                invoiceRowService.save(invoiceRow);
            }
        }
        if (invoice.getCustomer() != null)
            invoice_DB.setCustomer(invoice.getCustomer());
        if (invoice.getTrackingNo() != null)
            invoice_DB.setTrackingNo(invoice.getTrackingNo());
        if (invoice.getTotalPrice() != (BigDecimal.ZERO)) {
            invoice_DB.setTotalPrice(invoice.getTotalPrice());
        }
        invoiceRepository.save(invoice_DB);
    }

    public void deleteInvoice(String id) {
        invoiceRepository.delete(id);
    }


}
