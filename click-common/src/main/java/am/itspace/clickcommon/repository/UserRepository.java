package am.itspace.clickcommon.repository;

import am.itspace.clickcommon.model.User;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;

@SpringBootApplication

public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String s);
    User findByToken(String token);
}
