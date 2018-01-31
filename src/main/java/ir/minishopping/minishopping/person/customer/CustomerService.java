package ir.minishopping.minishopping.person.customer;

import com.querydsl.jpa.impl.JPAQueryFactory;
import ir.minishopping.minishopping.common.CodeGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class CustomerService {


    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        customerRepository.findAll().forEach(customers::add);
        return customers;
    }

    public Customer getCustomer(String id) {
        return customerRepository.findOne(id);
    }

    public void createCustomer(Customer customer) {

        if (customer.getEnable() == null)
            customer.setEnable(true);

        CodeGenerator codeGenerator = new CodeGenerator();
        customer.setCustomerCode(codeGenerator.randomUUID(5));

        customerRepository.save(customer);
    }

    public void updateCustomer(Customer customer, String id) {

        //about update for city, you just have to bind cityObj to customerDb.setCity
        //POSTMAN : in post man just put the id of city for binding a city to a customer
        //POSTMAN_ important point: get the city first.. after every rerun of application.

        Customer customerDb = customerRepository.findOne(id);//db obj

        if (customer.getEnable() == null)
            customer.setEnable(true);

        if (customer.getAge() != 0)
            customerDb.setAge(customer.getAge());
        if (customer.getTelNumber() != 0)
            customerDb.setTelNumber(customer.getTelNumber());
        if (customer.getCity() != null)
            customerDb.setCity(customer.getCity());
        if (customer.getEnable() != null)
            customerDb.setEnable(customer.getEnable());
        if (customer.getFirstName() != null)
            customerDb.setFirstName(customer.getFirstName());
        if (customer.getLastName() != null)
            customerDb.setLastName(customer.getLastName());

        customerRepository.save(customerDb);

    }

    public void deleteCustomer(String id) {
        customerRepository.delete(id);
    }

    @PersistenceContext
    private EntityManager entityManager;

    public Customer findCustomer() {


        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);

        QCustomer customer = QCustomer.customer;

        Customer sara =
                jpaQueryFactory.selectFrom(customer)
                        .where(customer.firstName.eq("Sara"))
                        .fetchOne();


        long xa = jpaQueryFactory.selectFrom(customer).fetchCount();
        log.info("xa {}"+xa);

        return sara;
    }

}
