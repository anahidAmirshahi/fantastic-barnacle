package ir.minishopping.minishopping.purchase.invoice;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, String>{

    Invoice findByTrackingNo(String trackingNo);

}
