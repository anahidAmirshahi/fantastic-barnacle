package ir.minishopping.minishopping.city;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CityController {

    @Autowired
    private CityService cityService;

    @GetMapping("/cities")
    public List<City> getAllCities() {
        return cityService.getAllCities();
    }

    @GetMapping("/cities/{id}")
    public City getCity(@PathVariable String id) {
        return cityService.getCity(id);
    }

    @PostMapping("/cities/init")
    public void initCity(){
        cityService.initCity();
    }

    @PostMapping("/cities")
    public City insertCity(@RequestBody City city) {
        cityService.insertCity(city);
        return city;
    }

    @PutMapping("/cities/{id}")
    public void updateCity(@RequestBody City city, @PathVariable String id) {

        if(city.getEnable() == null)
            city.setEnable(true);

        cityService.updateCity(city, id);
    }

    @DeleteMapping("cities/{id}")
    public void deleteCity(@PathVariable String id) {
        cityService.deleteCity(id);
    }
}
