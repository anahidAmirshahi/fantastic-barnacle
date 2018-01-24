package ir.minishopping.minishopping.person.customer;


import org.springframework.data.repository.CrudRepository;


public interface CustomerRepository extends CrudRepository<Customer,String> {

    //public Customer findById(String id);
}
