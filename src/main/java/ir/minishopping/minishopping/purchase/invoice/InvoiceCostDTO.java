package ir.minishopping.minishopping.purchase.invoice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class InvoiceCostDTO{

    int totalCreated;
    BigDecimal totalAmount;
    int totalSell;

}
