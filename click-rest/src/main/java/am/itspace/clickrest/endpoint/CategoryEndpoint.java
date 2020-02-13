package am.itspace.clickrest.endpoint;

import am.itspace.clickcommon.model.Category;
import am.itspace.clickcommon.service.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/rest/categories/")
@RestController
public class CategoryEndpoint {
    private final CategoryService categoryService;

    public CategoryEndpoint(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @GetMapping
    public List<Category> findAll(){
        return categoryService.findAll();
    }
}
