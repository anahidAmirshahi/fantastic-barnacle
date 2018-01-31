package ir.minishopping.minishopping.person.customer;

import com.querydsl.core.types.dsl.NumberExpression;
import ir.minishopping.minishopping.purchase.InvoiceDTO;
import ir.minishopping.minishopping.purchase.invoice.InvoiceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j // logger - for better printing. log.info for application. log.debug for develop side.
@RestController
public class CustomerController {

    //you could change this part with generating customizers!
    @Autowired
    private CustomerService customerService;
    @Autowired
    private InvoiceService invoiceService;


    @GetMapping("/customers")
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/customers/{id}")
    public Customer getCustomer(@PathVariable String id) {
        return customerService.getCustomer(id);
    }

    @PostMapping("/customers")
    public Customer createCustomer(@RequestBody Customer customer) {
        customerService.createCustomer(customer);
        return customer;
    }

    // ---------------- INVOICE ----------------
    @PostMapping("/customers/{id}/invoice")
    public String createInvoice(@RequestBody List<InvoiceDTO> invoiceDTOs, @PathVariable String id) {
        return invoiceService.createInvoice(invoiceDTOs, id); //follow this method in InvoiceService
    }

    @PutMapping("/customers/{id}")
    public void updateCustomer(@RequestBody Customer customer, @PathVariable String id) {
        customerService.updateCustomer(customer, id);
    }

    @DeleteMapping("/customers/{id}")
    public void deleteCustomer(@PathVariable String id) {
        customerService.deleteCustomer(id);
    }



    @GetMapping("/customers/querydsl")
    public Customer findCustomer(){
        return customerService.findCustomer();
    }
}
