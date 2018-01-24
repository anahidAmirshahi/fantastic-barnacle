package ir.minishopping.minishopping.person.customer;/*
package io.abstractsimple.personapi.person.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerInitializer implements ApplicationRunner{

    private final CustomerService customerService;// never new these type of class

    @Autowired
    public CustomerInitializer(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {


        Customer customer = new Customer();

        customer.setFirstName("Anahid");
        customer.setLastName("Amirshahi");
        customer.setAge(20);
        customer.setEnable(true);

        customerService.insertCustomer(customer);


    }
}
*/
