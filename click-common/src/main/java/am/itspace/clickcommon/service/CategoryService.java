package am.itspace.clickcommon.service;

import am.itspace.clickcommon.model.Category;

import java.util.List;

public interface CategoryService {

    List<Category> findAll();
    void addCategory(Category category);

    void deleteById(long id);
}
