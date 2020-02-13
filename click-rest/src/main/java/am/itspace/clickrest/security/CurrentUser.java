package am.itspace.clickrest.security;

import am.itspace.clickcommon.model.User;
import org.springframework.security.core.authority.AuthorityUtils;

public class CurrentUser extends  User {
    private User user;

    public CurrentUser(User user) {
        super(user.getEmail(), user.getPassword(), AuthorityUtils.createAuthorityList(user.getUserType().name()));
        this.user = user;
    }
    public User getUser() {
        return user;
    }

}
