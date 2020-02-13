package am.itspace.clickadmin.controller;

import am.itspace.clickcommon.model.User;
import am.itspace.clickcommon.model.UserType;
import am.itspace.clickcommon.service.UserService;
import am.itspace.clickadmin.security.CurrentUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
@RequestMapping("/admin")
public class MainController {


    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public MainController(UserService userService, PasswordEncoder passwordEncoder) {

        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    //go main paige boxed-layout
    @GetMapping("/")
    public String home(ModelMap modelMap, @AuthenticationPrincipal CurrentUser currentUser) {
        log.info("Home page was opened.");
        return "boxed-layout";
    }

    //go user page
    @GetMapping("/profile-user")
    public String userProfile(ModelMap modelMap, @RequestParam("userId") long userId) {
        User user = userService.findById(userId);
        modelMap.addAttribute("user", user);
        return "profile-about";
    }

    //go admin page
    @GetMapping("/profile-admin")
    public String adminProfile(@AuthenticationPrincipal CurrentUser currentUser, ModelMap modelMap) {
        if (currentUser != null) {
            modelMap.addAttribute("user", currentUser.getUser());
        }

        return "profile-admin";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("email") String email, @RequestParam("password") String password) {
        User byEmail = userService.findByEmail(email);
        if (passwordEncoder.matches(password, byEmail.getPassword()) && byEmail.getUserType() == UserType.ADMIN) {
            return "boxed-layout";
        }
        return "login";
    }


}
