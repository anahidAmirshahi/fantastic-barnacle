package ir.minishopping.minishopping.purchase.product;/*
package io.abstractsimple.personapi.purchase.product;

import io.abstractsimple.personapi.common.CodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.Instant;


@Configuration
public class ProductInitializer implements ApplicationRunner {

    private final ProductService productService;// never new these type of class

    @Autowired
    public ProductInitializer(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {



        Product product = new Product();
        product.setCountry("IR");
        CodeGenerator codeGenerator = new CodeGenerator();
        product.setCode(codeGenerator.randomUUID(8));
        product.setName("Antenna");
        product.setWeight(80d);
        product.setPurchaseDate(Instant.now().getEpochSecond());
        product.setPrice(BigDecimal.valueOf(200));
        product.setEnable(true);

        Product product1 = new Product();
        product1.setCountry("FRN");
        CodeGenerator codeGenerator1 = new CodeGenerator();
        product1.setCode(codeGenerator1.randomUUID(8));
        product1.setName("Modem");
        product1.setWeight(80d);
        product1.setPurchaseDate(Instant.now().getEpochSecond());
        product1.setPrice(BigDecimal.valueOf(400));
        product1.setEnable(true);

        productService.insertProduct(product);
        productService.insertProduct(product1);
    }
}
*/
