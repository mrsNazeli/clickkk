package am.itspace.clickcommon.repository;

import am.itspace.clickcommon.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ProductRepository extends JpaRepository<Product,Long> {

    List<Product> findAllByCategoryTitle(String title);
    List<Product> findAllByProductsTitle(String products_title);
    List<Product> findAllByUsersId(long id);
    List<Product> findByTitle(String keyword);
}
