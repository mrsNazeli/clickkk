package am.itspace.clickcommon.service.impl;

import am.itspace.clickcommon.model.ProductCategory;
import am.itspace.clickcommon.repository.ProductCategoryRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

//@Service
//@Slf4j
//public class ProductCategoryServiceImpl implements ProductCategoryService {
//
//    private final ProductCategoryRepository productCategoryRepository;
//
//    public ProductCategoryServiceImpl(ProductCategoryRepository productCategoryRepository) {
//        this.productCategoryRepository = productCategoryRepository;
//    }
//
//
//    @Override
//    public List<ProductCategory> findAll() {
//        return productCategoryRepository.findAll();
//    }
//
//    @Override
//    public void addCategory(ProductCategory productCategory) {
//        productCategoryRepository.save(productCategory);
//    }
//
//    @Override
//    public void deleteById(long id) {
//        productCategoryRepository.deleteById(id);
//    }
//}
