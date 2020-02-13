package am.itspace.clickadmin.controller;

import am.itspace.clickcommon.model.Category;
import am.itspace.clickcommon.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @PostMapping(value = "/addCategory")
    public String addCategory(Category category) {
        categoryService.addCategory(category);
        return "redirect:/boxed-layout";
    }

    @DeleteMapping("/deleteById")
    public String deleteById(@RequestParam("id") long id) {
        categoryService.deleteById(id);
        return "redirect:/boxed-layout";
    }
}
