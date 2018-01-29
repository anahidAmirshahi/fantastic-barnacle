package ir.minishopping.minishopping.purchase;

import lombok.Getter;

import java.io.Serializable;

@Getter//remember about GETTER
public enum InvoiceStatus implements Serializable {

    CREATED,//export time state
    SENT, //product sent to customer
    DELIVERED,
    CANCELED
}
