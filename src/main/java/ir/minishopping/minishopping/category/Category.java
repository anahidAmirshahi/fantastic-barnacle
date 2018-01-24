package ir.minishopping.minishopping.category;

import ir.minishopping.minishopping.common.BaseEntity;
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
public class Category extends BaseEntity {

    public String type;
    public String value;// for developing logic(s)

}
