package ir.minishopping.minishopping.purchase.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public List<Product> getAllProducts()    {
        return productService.getAllProducts();
    }

    @GetMapping("/products/{id}")
    public Product getProduct(@PathVariable String id)    {
        return productService.getProduct(id);
    }

    @PostMapping("/products")
    public Product insertProduct(@RequestBody Product product)    {
        productService.insertProduct(product);
        return product;
    }

    @PostMapping("/products/init")
    public void initProduct(){
        productService.initProduct();
    }

    @PutMapping("/products/{id}")
    public Product updateProduct(@RequestBody Product product,@PathVariable String id)    {
        productService.updateProduct(product,id);
        return product;
    }

    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable String id)     {
        productService.deleteProduct(id);
    }

}
