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

    @GetMapping("/invoices/costs")
    public InvoiceCostDTO getInvoicesCosts(){return invoiceService.getInvoicesCosts();}
}
