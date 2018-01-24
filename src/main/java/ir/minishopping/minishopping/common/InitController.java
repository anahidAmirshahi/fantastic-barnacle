package ir.minishopping.minishopping.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InitController {

    @Autowired
    private InitService initService;

    @PostMapping("/cities/init")
    public void initCity() {
        initService.initCity();
    }

    @PostMapping("/categories/init")
    public void initCategory(){
        initService.initCategory();
    }

    @PostMapping("/customers/init")
    public void initCustomer(){
        initService.initCustomer();
    }

    @PostMapping("/products/init")
    public void initProduct(){
        initService.initProduct();
    }


}
