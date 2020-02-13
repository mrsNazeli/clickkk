package am.itspace.clickrest.endpoint;

import am.itspace.clickcommon.model.ProductCategory;
import am.itspace.clickcommon.service.ProductCategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/rest/productCategories/")
@RestController
public class ProductCategoryEndpoint {
    private final ProductCategoryService productCategoryService;

    public ProductCategoryEndpoint(ProductCategoryService materialCategoryService) {
        this.productCategoryService = materialCategoryService;
    }

    @GetMapping
    public List<ProductCategory> findAll(){
        return  productCategoryService.findAll();
    }
}
