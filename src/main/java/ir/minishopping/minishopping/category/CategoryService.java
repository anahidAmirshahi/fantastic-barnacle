package ir.minishopping.minishopping.category;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;


    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        categoryRepository.findAll().forEach(categories::add);
        return categories;
    }

    public Category getCategory(String id) {
        return categoryRepository.findOne(id);
    }

    public void createCategory(Category category) {
        if(category.getEnable() == null)
            category.setEnable(true);

        categoryRepository.save(category);
    }

    public void updateCategory(Category category, String id) {

        Category category_DB = categoryRepository.findOne(id);

        if (category.getEnable() != null)
            category_DB.setEnable(category.getEnable());
        if (category.getType() != null)
            category_DB.setType(category.getType());

        categoryRepository.save(category_DB);
    }

    public void deleteCategory(String id) {
        categoryRepository.delete(id);
    }


}
