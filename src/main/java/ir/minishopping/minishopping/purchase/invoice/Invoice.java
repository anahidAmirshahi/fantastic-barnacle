package ir.minishopping.minishopping.purchase.invoice;

import ir.minishopping.minishopping.common.BaseEntity;
import ir.minishopping.minishopping.person.customer.Customer;
import ir.minishopping.minishopping.purchase.InvoiceStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Invoice extends BaseEntity {

    private BigDecimal totalPrice;
    private String trackingNo;
    private InvoiceStatus status;


    @OneToMany(mappedBy = "invoice")
    private List<InvoiceRow> invoiceRows;// to declare a OneToMany relation, you should return the List of InvoiceRow

    @ManyToOne
    private Customer customer;

}
