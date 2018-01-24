package ir.minishopping.minishopping.category;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories")
    public List<Category> getAllCategory()       {
        return categoryService.getAllCategories();
    }

    @GetMapping("/categories/{id}")
    public Category getCategory(@PathVariable String id)      {
        return categoryService.getCategory(id);
    }

    @PostMapping("/categories")
    public Category createCategory(@RequestBody Category category)   {
        categoryService.createCategory(category);
        return category;
    }

    @PutMapping("/categories/{id}")
    public void updateCategory(@RequestBody Category category,@PathVariable String id)   {
        categoryService.updateCategory(category,id);
    }

    @DeleteMapping("/categories/{id}")
    public void deleteCategory(@PathVariable String id)    {
        categoryService.deleteCategory(id);
    }




}
