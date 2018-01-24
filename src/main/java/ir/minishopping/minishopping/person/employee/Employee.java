package ir.minishopping.minishopping.person.employee;



import ir.minishopping.minishopping.person.Person;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity

public class Employee extends Person {

    private String employeeCode;

}
