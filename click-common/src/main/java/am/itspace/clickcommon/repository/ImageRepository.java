package am.itspace.clickcommon.repository;

import am.itspace.clickcommon.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ImageRepository extends JpaRepository<Image, Long> {

    List<Image> findAllByProductsId(long products_id);
    List<Image> findAllByMaterialsId(long material_id);
}
