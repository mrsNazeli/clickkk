package am.itspace.clickcommon.service.impl;

import am.itspace.clickcommon.model.Category;
import am.itspace.clickcommon.model.Image;
import am.itspace.clickcommon.model.Product;
import am.itspace.clickcommon.model.User;
import am.itspace.clickcommon.repository.CategoryRepository;
import am.itspace.clickcommon.repository.ImageRepository;
import am.itspace.clickcommon.repository.ProductRepository;
import am.itspace.clickcommon.repository.UserRepository;
import am.itspace.clickcommon.service.ProductService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    @Value("${image.upload.dir}")
    private String imageUploadDir;

    private ProductRepository productRepository;

    private final ImageRepository imageRepository;

    private final CategoryRepository categoryRepository;


    private ProductService productService;

    private final UserRepository userRepository;

    public ProductServiceImpl(ImageRepository imageRepository, CategoryRepository categoryRepository,  ProductRepository productRepository, ProductService productService, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.imageRepository = imageRepository;
        this.categoryRepository = categoryRepository;
        this.productService = productService;

        this.productRepository = productRepository;
        this.productService = productService;
        this.userRepository = userRepository;
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> findAllByCategory(String title) {
        return productRepository.findAllByCategoryTitle(title);
    }

    @Override
    public List<Product> findAllByMaterials(String  title) {
        return productRepository.findAllByProductsTitle(title);
    }

    @Override
    public Product findById(long id) {
        return productRepository.getOne(id);
    }

    @Override
    public Page<Product> findByPageable(Pageable pageable) {
        return null;
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }


        List<Long> products;
        List<Product> productList = productService.addProducts(products);


    @Override
    public void deleteById(long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product createProduct(MultipartFile [] multipartFile, double height, double width, List<Long> products, Product product, String answer,
                                 Category category, String title, String desc, int count, User user) {
       double invoicePrice = 0;
       List<Product> productList1 = new ArrayList<>();
        for (Long product1 : products) {
            Product one = productRepository.getOne(product1);
            productList1.add(one);
        }
        for (Product product1 : productList) {
            double invoicePrice1 = product.getPrice();
          invoicePrice += invoicePrice1;
        }
        Image image = null;
        List<Image> images = new ArrayList<>();
        for (MultipartFile file : multipartFile) {
            String picUrl = UUID.randomUUID() + "_" + file.getOriginalFilename();
            File file1 = new File(imageUploadDir, picUrl);
            images = (List<Image>) new Image();
            image.setName(picUrl);
            try {
                file.transferTo(file1);
            } catch (IOException e) {
                e.printStackTrace();
            }
            images.add((Image) image);
            imageRepository.save(image);
        }

        Product product1 = product;
        List<Product> productList = new ArrayList<>();
        productList.add(product1);
        if (answer.equals("yes")){
            productRepository.save(product1);
            user.setProducts(productList);
        }
        return product;
    }



    @Override
    public List<Product> addProducts(List<Long> products) {
        List<Product> productList = new ArrayList<>();
        for (Long product : products) {
            Product byId = findById(product);
            productList.add(byId);
        }

        return productList;
    }

    @Override
    public List<Product> findAllByUserId(long id) {
        return productRepository.findAllByUsersId(id);
    }

    @Override
    public List<Product> findAllByName(String keyword) {
        return productRepository.findByTitle(keyword);
    }

    @Override
    public void save(Product product, MultipartFile[] multipartFile, long category_id) {

    }
}
