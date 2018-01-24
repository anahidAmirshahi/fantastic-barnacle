package ir.minishopping.minishopping.purchase.invoice;

import ir.minishopping.minishopping.common.CodeGenerator;
import ir.minishopping.minishopping.person.customer.Customer;
import ir.minishopping.minishopping.person.customer.CustomerRepository;
import ir.minishopping.minishopping.purchase.InvoiceDTO;
import ir.minishopping.minishopping.purchase.product.Product;
import ir.minishopping.minishopping.purchase.product.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
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


    public Page<Invoice> getAllInvoices(Pageable pageable)   {

        return invoiceRepository.findAll(pageable);

    }

    public Invoice getInvoice(String id)     {
        return invoiceRepository.findOne(id);
    }

    public Invoice trackInvoice(String trackingNo)   {
        return invoiceRepository.findByTrackingNo(trackingNo);
    }

    public void insertInvoice(Invoice invoice)     {

        if (invoice.getEnable() == null)
            invoice.setEnable(true);

        CodeGenerator codeGenerator = new CodeGenerator();
        invoice.setTrackingNo(codeGenerator.randomUUID(10));

        invoiceRepository.save(invoice);
    }

    public void updateInvoice(Invoice invoice, String id)   {

        Invoice invoice_DB = invoiceRepository.findOne(id);

        if (invoice.getEnable() != null)
            invoice_DB.setEnable(invoice.getEnable());
        if(invoice.getCustomer() != null)
            invoice_DB.setCustomer(invoice.getCustomer());
        if(invoice.getInvoiceRows() != null)
            invoice_DB.setInvoiceRows(invoice.getInvoiceRows());
        if(invoice.getTrackingNo() != null)
            invoice_DB.setTrackingNo(invoice.getTrackingNo());
        //compare big decimal with zero
        if(invoice.getTotalPrice().compareTo(BigDecimal.ZERO) != 0)
            invoice_DB.setTotalPrice(invoice.getTotalPrice());

        invoiceRepository.save(invoice_DB);
    }

    public void deleteInvoice(String id)    {
        invoiceRepository.delete(id);
    }


    public String createInvoice(List<InvoiceDTO> invoiceDTOs, String id)  {

        Customer customer = customerRepository.findOne(id);

        List<InvoiceRow> invoiceRowssss = new ArrayList<>();

        BigDecimal totalPrice = BigDecimal.valueOf(0);

        //log.info("  > > >  productIds {}", productIds);//log instead of SOUT.

        Invoice invoice = new Invoice();

        invoice.setCustomer(customer);

        invoice.setEnable(true);

        CodeGenerator codeGenerator = new CodeGenerator();
        String trackingNo = codeGenerator.randomUUID(10);
        invoice.setTrackingNo(trackingNo);

        invoiceRepository.save(invoice);

        for(InvoiceDTO invoiceDTO : invoiceDTOs){

            InvoiceRow invoiceRow = new InvoiceRow();

            Product product = productRepository.findOne(invoiceDTO.getProductId());

            int amount = 1;

            if(invoiceDTO.getAmount() != null)
                amount = invoiceDTO.getAmount();

            if(product.getPrice() != null)
                totalPrice = totalPrice.add(BigDecimal.valueOf(amount).multiply(product.getPrice()));

            invoiceRow.setCount(invoiceDTO.getAmount());
            invoiceRow.setProduct(product);
            invoiceRow.setEnable(true);
            invoiceRow.setInvoice(invoice);

            invoiceRowService.save(invoiceRow);
            invoiceRowssss.add(invoiceRow);
        }

        log.info("totalPrice {}",totalPrice);

        invoice.setInvoiceRows(invoiceRowssss);
        invoice.setTotalPrice(totalPrice);

        invoiceRepository.save(invoice);

        return trackingNo;


    }


}
