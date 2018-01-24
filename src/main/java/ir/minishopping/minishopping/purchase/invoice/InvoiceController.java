package ir.minishopping.minishopping.purchase.invoice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @GetMapping("/invoices")
    public Page<Invoice> getAllInvoices(Pageable pageable) {
        return invoiceService.getAllInvoices(pageable);
    }

    @GetMapping("/invoices/{id}")
    public Invoice getInvoice(@PathVariable String id) {
        return invoiceService.getInvoice(id);
    }

    @GetMapping("/invoices/tracking/{id}")
    public Invoice trackInvoice(@PathVariable String id) {
        return invoiceService.trackInvoice(id);
    }

    @PutMapping("/invoices/{id}")
    public void updateInvoice(@RequestBody Invoice invoice, @PathVariable String id) {
        invoiceService.updateInvoice(invoice, id);
    }

    @DeleteMapping("/invoices/{id}")
    public void deleteInvoice(@PathVariable String id) {
        invoiceService.deleteInvoice(id);
    }


    /*    @PostMapping("/invoices")
    @ResponseBody
    public String insertInvoice(@RequestBody Invoice invoice) {
        invoiceService.insertInvoice(invoice);
        return "Thanks for your purchase, invoice created successfully, tracking number : : " + invoice.getTrackingNo();
    }*/

/*    // to add one row -- NOT CORRECT IDEA --
    @PostMapping("invoice/addRow")
    @ResponseBody
    public Invoice addInvoice(@RequestBody InvoiceRow invoiceRow) {

        invoiceRowService.save(invoiceRow);

        Invoice invoice = new Invoice();
        //Customer customer = new Customer();

        List<InvoiceRow> rows = new ArrayList<>();
        rows.add(invoiceRow);

        invoice.setInvoiceRows(rows);
        //invoice.setCustomer(customer);
        invoice.setEnable(true);

        CodeGenerator codeGenerator = new CodeGenerator();
        invoice.setTrackingNo(codeGenerator.randomUUID(10));


        BigDecimal price = invoiceRow.getProduct().getPrice();
        BigDecimal cnt = BigDecimal.valueOf(invoiceRow.getCount());

        invoice.setTotalPrice(price.multiply(cnt));

        invoiceService.insertInvoice(invoice);
        //return "Thanks for your purchase, invoice create successfully, tracking number : : " + invoice.getTrackingNo();




        return invoice;
    }*/


}
