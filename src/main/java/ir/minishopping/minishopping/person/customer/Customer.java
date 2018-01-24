package ir.minishopping.minishopping.person.customer;

import ir.minishopping.minishopping.city.City;
import ir.minishopping.minishopping.person.Person;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity

public class Customer extends Person {

    private String customerCode;

    @ManyToOne
    private City city;

}
