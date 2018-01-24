package ir.minishopping.minishopping.common;

import ir.minishopping.minishopping.category.Category;
import ir.minishopping.minishopping.category.CategoryRepository;
import ir.minishopping.minishopping.city.City;
import ir.minishopping.minishopping.city.CityRepository;
import ir.minishopping.minishopping.person.customer.Customer;
import ir.minishopping.minishopping.person.customer.CustomerRepository;
import ir.minishopping.minishopping.purchase.product.Product;
import ir.minishopping.minishopping.purchase.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;

@Service
public class InitService {

    @Autowired
    CityRepository cityRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ProductRepository productRepository;

    public void initCity() {

        List<String> cities = Arrays.asList(
                "Tehran", "Isfahan", "Kerman", "Khozestan", "Azarbayjan",
                "Lorestan", "Khorasan", "Sistan Baloochestan", "Qazvin", "Fars"
        );

        for (String c : cities) {
            City city = new City();
            city.setCityName(c);
            city.setEnable(true);
            cityRepository.save(city);
        }

    }

    public void initCategory() {
        Category category = new Category();
        category.setType("x");
        category.setValue("xy");
        categoryRepository.save(category);
    }

    public void initCustomer() {
        Customer customer = new Customer();

        customer.setFirstName("Anahid");
        customer.setLastName("Amirshahi");
        CodeGenerator codeGenerator = new CodeGenerator();
        customer.setCustomerCode(codeGenerator.randomUUID(5));
        customer.setAge(20);
        customer.setEnable(true);

        customerRepository.save(customer);
    }

    public void initProduct() {
        Product product = new Product();
        product.setCountry("IR");
        CodeGenerator codeGenerator1 = new CodeGenerator();
        product.setCode(codeGenerator1.randomUUID(8));
        product.setName("Antenna");
        product.setWeight(80d);
        product.setPurchaseDate(Instant.now().getEpochSecond());
        product.setPrice(BigDecimal.valueOf(200));
        product.setEnable(true);

        Product product1 = new Product();
        product1.setCountry("FRN");
        CodeGenerator codeGenerator2 = new CodeGenerator();
        product1.setCode(codeGenerator2.randomUUID(8));
        product1.setName("Modem");
        product1.setWeight(80d);
        product1.setPurchaseDate(Instant.now().getEpochSecond());
        product1.setPrice(BigDecimal.valueOf(400));
        product1.setEnable(true);

        productRepository.save(product);
        productRepository.save(product1);
    }
}
