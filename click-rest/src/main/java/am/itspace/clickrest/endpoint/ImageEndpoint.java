package am.itspace.clickrest.endpoint;

import am.itspace.clickcommon.model.Image;
import am.itspace.clickcommon.service.ImageService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/rest/images/")
@RestController
public class ImageEndpoint {

    private final ImageService imageService;

    public ImageEndpoint(ImageService imageService) {
        this.imageService = imageService;
    }
    @GetMapping("/product")
    public List<Image> findAllByProductId(@RequestParam("product_id") long product_id) {
        return imageService.findAllByProductId(product_id);

    }
    @GetMapping("{id}")
    public ResponseEntity findById(@PathVariable("id") long id){
        return ResponseEntity.ok(imageService.findById(id));
    }
    @GetMapping("all")
    public List<Image> findAll() {
        return imageService.findAll();
    }

    @GetMapping(value = "getImage", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody
    byte[] getImage(@RequestParam("imageUrl") String imageUrl) {
        return imageService.getImage(imageUrl);
    }

}
