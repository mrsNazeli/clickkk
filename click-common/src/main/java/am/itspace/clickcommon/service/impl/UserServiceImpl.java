package am.itspace.clickcommon.service.impl;

import am.itspace.clickcommon.model.Address;
import am.itspace.clickcommon.model.Image;
import am.itspace.clickcommon.model.User;
import am.itspace.clickcommon.repository.ImageRepository;
import am.itspace.clickcommon.repository.ProductRepository;
import am.itspace.clickcommon.repository.UserRepository;
import am.itspace.clickcommon.service.EmailService;
import am.itspace.clickcommon.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Value("${image.upload.dir}")
    private String imageUploadDir;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final EmailService emailService;

    private final ImageRepository imageRepository;

    private final ProductRepository productRepository;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, EmailService emailService, ImageRepository imageRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
        this.imageRepository = imageRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void register(User user, MultipartFile multipartFile, Address address) throws IOException {
        String imageName = multipartFile.getOriginalFilename();
        imageName = UUID.randomUUID() + "_" + imageName;
        File file = new File(imageUploadDir, imageName);
        multipartFile.transferTo(file);
        am.itspace.clickcommon.model.Image image = new Image();
        image.setName(imageName);
        imageRepository.save(image);
        user = User.builder()
                .password(passwordEncoder.encode(user.getPassword()))
                .isEnable(false)
                .token(UUID.randomUUID().toString())
                .image(image)
                .address(address)
                .build();

        userRepository.save(user);
        String link = "http://localhost:8080/activate?token=" + user.getToken();
        emailService.sendSimpleMessage(user.getEmail(),
                "Welcome",
                "Congratulations! Dear " + user.getName() + " " + user.getSurname() + " have successfully register to system! \n" +
                        "You have to activate your account by this link " + link);
    }

    @Override
    public void activate(String token) {
        User byToken = userRepository.findByToken(token);
        if (byToken != null) {
            byToken.setEnable(true);
            byToken.setToken(null);
            userRepository.save(byToken);
            emailService.sendMessageWithAttachment(byToken.getEmail(), "Success", "You success registered", "D:\\003\\102CANON\\IMG_1225.JPG");
        }
    }


    @Override
    public boolean isEmailExists(String email) {
        return userRepository.findByEmail(email) != null;
    }

//
//    public boolean isExists(String email) {
//        return userRepository.findByEmail(email).isPresent();
//    }


    @Override
    public void deleteById(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findByEmail(String email) {
        Optional<User> byEmail = Optional.ofNullable(userRepository.findByEmail(email));
        return byEmail.get();
    }


    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(long id) {
        return userRepository.getOne(id);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public boolean isExists(String email) {
        return false;
    }
}
