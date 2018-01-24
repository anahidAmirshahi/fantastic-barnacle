package ir.minishopping.minishopping.purchase.invoice;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ir.minishopping.minishopping.common.BaseEntity;
import ir.minishopping.minishopping.purchase.product.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class InvoiceRow extends BaseEntity {

    @OneToOne
    private Product product;

    @JsonIgnore
    @ManyToOne
    private Invoice invoice;

    public int count;

}
