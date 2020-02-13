package am.itspace.clickadmin.controller;

import am.itspace.clickcommon.model.Image;
import am.itspace.clickcommon.service.ImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/admin/images")
public class ImageController {

    @Value("${image.upload.dir}")
    private String imageUploadDir;

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    //getImage method
    @GetMapping(value = "/getImage", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody
    byte[] getImage(@RequestParam("picUrl") String picUrl) throws IOException {
        return imageService.getImage(picUrl);
    }

    //find all products
    @GetMapping("/allImages")
    public String findAll(ModelMap modelMap) {
        List<Image> all = imageService.findAll();
        modelMap.addAttribute("all", all);
        return "photo-gallery";
    }

    @DeleteMapping("/deleteById")
    public String deleteById(@RequestParam("id") long id) {
        imageService.deleteById(id);
        return "redirect:/photo_gallery";
    }

    @PostMapping("/addImages")
    public String addImages(@RequestParam(value = "images") MultipartFile[] file) {
        try {
            imageService.addImages(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/boxed-layout";
    }

    @PostMapping("/addImage")
    public String addImage(@RequestParam(value = "image") MultipartFile file) {
        try {
            imageService.addImage(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/boxed-layout";
    }

    @GetMapping("/single/image")
    public String single(@RequestParam("id") int id, ModelMap modelMap) {
        Image image = imageService.findById(id);
        if (image != null) {
            modelMap.addAttribute("image", image);
        } else {
            log.error("Image with {} id does not exists", id);
        }
        return "singleImage";
    }
}
