package am.itspace.clickrest.endpoint;

import am.itspace.clickcommon.model.Product;
import am.itspace.clickcommon.service.ImageService;
import am.itspace.clickcommon.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/rest/products/")

@RestController
public class ProductEndpoint {

    private final ProductService productService;
    private final ImageService imageService;

    public ProductEndpoint(ProductService productService, ImageService imageService) {
        this.productService = productService;
        this.imageService = imageService;
    }
    @GetMapping
    public List<Product> findAll() {
        return productService.findAll();

    }

    @GetMapping("{id}")
    public ResponseEntity findById(@PathVariable("id") long id) {
        return ResponseEntity.ok(productService.findById(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteById(@PathVariable("id") long id) {
        productService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/category")
    public List<Product> findAllByCategory(@RequestParam("title") String title) {
        return productService.findAllByCategory(title);
    }

}
