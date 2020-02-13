package am.itspace.clickcommon.service;

import am.itspace.clickcommon.model.ProductCategory;

import java.util.List;

public interface ProductCategoryService {

    List<ProductCategory> findAll();
    void addCategory(ProductCategory productCategory);

    void deleteById(long id);
}
