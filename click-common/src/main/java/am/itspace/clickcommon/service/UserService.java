package am.itspace.clickcommon.service;

import am.itspace.clickcommon.model.Address;
import am.itspace.clickcommon.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface UserService {
    List<User> findAll();

    void register(User user, MultipartFile multipartFile, Address address) throws IOException;

    void activate(String token);

    boolean isEmailExists(String email);

    User findById(long id);

    void save(User user);

    boolean isExists(String email);

    void deleteById(long id);

    User findByEmail(String email);

}
