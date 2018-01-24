package ir.minishopping.minishopping.purchase;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor


//DTO

public class InvoiceDTO {

    private String productId;
    private Integer amount;
}
