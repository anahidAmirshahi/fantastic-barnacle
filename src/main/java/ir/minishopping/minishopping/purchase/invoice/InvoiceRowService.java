package ir.minishopping.minishopping.purchase.invoice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class InvoiceRowService {

    @Autowired
    private InvoiceRowRepository invoiceRowRepository;

    public void save(InvoiceRow invoiceRow){
        invoiceRowRepository.save(invoiceRow);
    }

}
