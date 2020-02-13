package am.itspace.clickadmin.security;

import com.example.common.model.User;
import lombok.Data;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;

@Data
public class CurrentUser extends org.springframework.security.core.userdetails.User {

    private User user;

    public CurrentUser(User user) {
        super(user.getEmail(), user.getPassword(),
                user.isEnable(), true, true, true, Arrays.asList(new SimpleGrantedAuthority(user.getUserType().name())));

        this.user = user;
    }

}
