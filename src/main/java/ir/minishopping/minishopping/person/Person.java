package ir.minishopping.minishopping.person;

import ir.minishopping.minishopping.common.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.MappedSuperclass;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

@MappedSuperclass
public abstract class Person extends BaseEntity {

    private String firstName;
    private String lastName;
    private int age;
    private int telNumber;

}
