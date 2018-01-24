package ir.minishopping.minishopping.purchase.product;

import ir.minishopping.minishopping.category.Category;
import ir.minishopping.minishopping.common.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.math.BigDecimal;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Product extends BaseEntity {

    private String name;
    private String country;
    private String code;
    private long purchaseDate;
    private BigDecimal price;
    private double weight;

    @OneToOne
    private Category category;

}
