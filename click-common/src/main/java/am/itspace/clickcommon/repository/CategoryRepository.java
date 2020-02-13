package am.itspace.clickcommon.repository;

import am.itspace.clickcommon.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;



public interface CategoryRepository extends JpaRepository<Category,Long> {
}
