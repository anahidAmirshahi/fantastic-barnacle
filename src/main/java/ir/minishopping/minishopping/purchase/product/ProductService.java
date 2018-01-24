package ir.minishopping.minishopping.purchase.product;

import ir.minishopping.minishopping.common.CodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;


    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        productRepository.findAll().forEach(products::add);
        return products;
    }

    public Product getProduct(String id) {
        return productRepository.findOne(id);
    }

    public void createProduct(Product product) {

        CodeGenerator codeGenerator = new CodeGenerator();
        product.setCode(codeGenerator.randomUUID(10));

        if (product.getEnable() == null)
            product.setEnable(true);


        productRepository.save(product);
    }

    public void updateProduct(Product product, String id) {

        Product product_DB = productRepository.findOne(id);

        if (product.getEnable() != null)
            product_DB.setEnable(product.getEnable());
        if (product.getCode() != null)
            product_DB.setCode(product.getCode());
        if (product.getCountry() != null)
            product_DB.setCountry(product.getCountry());
        if (product.getName() != null)
            product_DB.setName(product.getName());
        if (product.getWeight() != 0)
            product_DB.setWeight(product.getWeight());

        //Compare bigDecimal to zero
        if (product.getPrice().compareTo(BigDecimal.ZERO) != 0)
            product_DB.setPrice(product.getPrice());


        productRepository.save(product_DB);
    }

    public void deleteProduct(String id) {
        productRepository.delete(id);
    }
}
