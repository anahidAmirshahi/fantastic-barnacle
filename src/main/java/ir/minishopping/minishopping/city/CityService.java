package ir.minishopping.minishopping.city;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    public List<City> getAllCities() {
        List<City> cities = new ArrayList<>();
        cityRepository.findAll().forEach(cities::add);//Lambda
        return cities;
    }

    public City getCity(String id) {
        return cityRepository.findOne(id);
    }

    public void insertCity(City city) {
        cityRepository.save(city);
    }

    public void updateCity(City city, String id) {

        City city_DB = cityRepository.findOne(id);

        if(city.getCityName() != null)
            city_DB.setCityName(city.getCityName());
        if(city.getEnable() != null)
            city_DB.setEnable(city.getEnable());

        cityRepository.save(city_DB);
    }

    public void deleteCity(String id) {
        cityRepository.delete(id);
    }

    public void initCity(){

        List<String> cities = Arrays.asList(
                "Tehran","Isfahan","Kerman","Khozestan","Azarbayjan",
                "Lorestan","Khorasan","Sistan Baloochestan","Qazvin","Fars"
        );

        for (String c:cities) {
            City city = new City();
            city.setCityName(c);
            city.setEnable(true);
            cityRepository.save(city);
        }

    }

}
