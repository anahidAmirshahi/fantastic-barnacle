package ir.minishopping.minishopping.city;

/*


package io.abstractsimple.personapi.city;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;


@Configuration // @Component this annotation inform the app, before startup, care to this code.
public class CityInitializer implements ApplicationRunner {

    private final CityService cityService;// never new these type of class

    @Autowired
    public CityInitializer(CityService cityService) {
        this.cityService = cityService;
    }

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {


        List<String> cities = Arrays.asList(
                "Tehran","Isfahan","Kerman","Khozestan","Azarbayjan",
                "Lorestan","Khorasan","Sistan Baloochestan","Qazvin","Fars"
        );

        for (String c:cities) {
            City city = new City();
            city.setCityName(c);
            city.setEnable(true);
            cityService.insertCity(city);
        }


    }
}
*/
