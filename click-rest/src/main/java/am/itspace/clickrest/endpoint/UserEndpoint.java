package am.itspace.clickrest.endpoint;

import am.itspace.clickrest.security.CurrentUser;
import am.itspace.clickrest.security.JwtTokenUtil;
import am.itspace.clickcommon.model.Address;
import am.itspace.clickcommon.model.Image;
import am.itspace.clickcommon.model.Product;
import am.itspace.clickcommon.model.User;
import am.itspace.clickcommon.service.AddressService;
import am.itspace.clickcommon.service.ImageService;
import am.itspace.clickcommon.service.ProductService;
import am.itspace.clickcommon.service.UserService;
import am.itspace.clickrest.dto.AuthenticationRequest;
import am.itspace.clickrest.dto.AuthenticationResponse;
import am.itspace.clickrest.dto.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@RestController
public class UserEndpoint {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;
    private final ImageService imageService;
    private final AddressService addressService;
    private final ProductService productService;

    public UserEndpoint(UserService userService, PasswordEncoder passwordEncoder, JwtTokenUtil jwtTokenUtil, ImageService imageService, AddressService addressService, ProductService productService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenUtil = jwtTokenUtil;
        this.imageService = imageService;
        this.addressService = addressService;
        this.productService = productService;
    }

    @PostMapping("auth")
    public ResponseEntity auth(@RequestBody AuthenticationRequest authenticationRequest) {
        User user = userService.findByEmail(authenticationRequest.getEmail());
        if (user != null && passwordEncoder.matches(authenticationRequest.getPassword(), user.getPassword())
        ) {
            String token = jwtTokenUtil.generateToken(user.getEmail());
            return ResponseEntity.ok(AuthenticationResponse.builder()
                    .token(token)
                    .userDto(UserDto.builder()
                            .id((int) user.getId())
                            .name(user.getName())
                            .surname(user.getSurname())
                            .email(user.getEmail())
                            .userType(user.getUserType())
                            .build())
            );
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
    }

    @PutMapping("addImage/userId")
    public ResponseEntity addImage(@AuthenticationPrincipal CurrentUser currentUser, @RequestParam(value = "file") MultipartFile file) {

        try {
            User byId = userService.findById(currentUser.getUser().getId());
            Image image = imageService.addImage(file);
            byId.setImage(image);
            userService.save(byId);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok().build();

    }
    @GetMapping("id")
    public ResponseEntity findUserById(@AuthenticationPrincipal CurrentUser user){
        User byId=userService.findById(user.getUser().getId());
        return ResponseEntity.ok(byId);
    }
    @PostMapping
    public ResponseEntity saveUser(@RequestBody User user){
        if (userService.isExists(user.getEmail())){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.save(user);
        return  ResponseEntity.ok("user was success");
    }
    @PostMapping("address")
    public void saveAddress(@RequestBody Address address,@AuthenticationPrincipal CurrentUser user){
        User byId = userService.findById(user.getUser().getId());
        byId.setAddress(address);
        addressService.save(address);
        userService.save(byId);
    }

    @PostMapping("products")
    public void addProducts(@RequestParam("products") List<Long> products, @AuthenticationPrincipal CurrentUser user){
        User byId = userService.findById(user.getUser().getId());
        List<Product> productList = productService.addProducts(products);
        byId.setProducts(productList);
        userService.save(byId);
    }
}



