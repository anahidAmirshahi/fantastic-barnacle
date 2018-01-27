package ir.minishopping.minishopping.purchase.invoice;

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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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

    //TODO all about pageable
    public Page<Invoice> getAllInvoices(Pageable pageable)   {
        return invoiceRepository.findAll(pageable);
    }

    public Invoice getInvoice(String id)    {
        return invoiceRepository.findOne(id);
    }

    public Invoice trackInvoice(String trackingNo)    {
        return invoiceRepository.findByTrackingNo(trackingNo);
    }

    public InvoiceCostDTO getInvoicesCosts() {
        InvoiceCostDTO invoiceCostDTO = new InvoiceCostDTO();
        List<Invoice> invoices = invoiceRepository.findAll();

        invoiceCostDTO.setTotalCost(BigDecimal.ZERO);
        invoiceCostDTO.setTotalInvoices(0);
        invoiceCostDTO.setTotalSell(0);

        for (Invoice i : invoices) {
            invoiceCostDTO.setTotalCost(invoiceCostDTO.getTotalCost().add(i.getTotalPrice()));
            for (InvoiceRow ir : i.getInvoiceRows())
                invoiceCostDTO.setTotalSell(invoiceCostDTO.getTotalSell() + ir.getCount());
        }

        invoiceCostDTO.setTotalInvoices(invoices.size());
        return invoiceCostDTO;
    }

    public String createInvoice(List<InvoiceDTO> invoiceDTOs, String id) {

        Customer customer = customerRepository.findOne(id);

        List<InvoiceRow> invoiceRowList = new ArrayList<>();//log.info(" >invoiceRowList {}", invoiceRowList);

        Invoice invoice = new Invoice();
        invoice.setCustomer(customer);
        invoice.setEnable(true);

        invoice.setStatus(InvoiceStatus.EXPORTED);

        CodeGenerator codeGenerator = new CodeGenerator();
        String trackingNo = codeGenerator.randomUUID(10);

        invoice.setTrackingNo(trackingNo);

        invoiceRepository.save(invoice);

        BigDecimal totalPrice = BigDecimal.valueOf(0);

        for (InvoiceDTO invoiceDTO : invoiceDTOs) {

            InvoiceRow invoiceRow = new InvoiceRow();

            Product product = productRepository.findOne(invoiceDTO.getProductId());

            int amount = (invoiceDTO.getAmount() != null) ? invoiceDTO.getAmount() : 1;

            if (product.getExistCount() < amount) {
                throw new InvoiceException("Not enough storage of this product is available.");
            } else if (product.getPrice() != null) {
                totalPrice = totalPrice.add(BigDecimal.valueOf(amount).multiply(product.getPrice()));

                invoiceRow.setCount(amount);
                invoiceRow.setProduct(product);
                invoiceRow.setEnable(true);
                invoiceRow.setInvoice(invoice);

                invoiceRowService.save(invoiceRow);
                invoiceRowList.add(invoiceRow);
            }
        }

        invoice.setInvoiceRows(invoiceRowList);
        invoice.setTotalPrice(totalPrice);

        invoiceRepository.save(invoice);

        return " Thanks for your purchase, tracking number : " + trackingNo;
    }

    public void updateInvoice(Invoice invoice, String id) {

        Invoice invoice_DB = invoiceRepository.findOne(id);

        if (invoice.getEnable() != null)
            invoice_DB.setEnable(invoice.getEnable());
        if (invoice.getCustomer() != null)
            invoice_DB.setCustomer(invoice.getCustomer());
        if (invoice.getInvoiceRows() != null)
            invoice_DB.setInvoiceRows(invoice.getInvoiceRows());
        if (invoice.getTrackingNo() != null)
            invoice_DB.setTrackingNo(invoice.getTrackingNo());
        if (invoice.getTotalPrice().compareTo(BigDecimal.ZERO) != 0)        //compare big decimal with zero
            invoice_DB.setTotalPrice(invoice.getTotalPrice());

        invoiceRepository.save(invoice_DB);
    }

    public void deleteInvoice(String id) {
        invoiceRepository.delete(id);
    }

}
