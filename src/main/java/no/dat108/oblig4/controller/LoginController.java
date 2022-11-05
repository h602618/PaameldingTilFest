package no.dat108.oblig4.controller;

import no.dat108.oblig4.model.User;
import no.dat108.oblig4.model.UserService;
import no.dat108.oblig4.utils.LoginUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/${app.url.login}")
public class LoginController {
    @Value("${app.url.login}")
    private String LOGIN_URL;

    @Value("${app.url.participants}")
    private String PARTICIPANTS_URL;

    @Autowired
    private UserService userService;

    @GetMapping
    public String showForm() {
        String pass = "pass";
        String salt = LoginUtil.generateSalt();
        String encoded = LoginUtil.generateHash(pass, salt);
        System.out.println(encoded);
        String encoded2 = LoginUtil.generateHash(pass, salt);
        System.out.println(encoded2);
//        System.out.println(String.format("pass: %s", pass));
//        System.out.println(String.format("pass: %s", encoded));
//        System.out.println(String.format("pass: %s", encoded2));
//        System.out.println(LoginUtil.validate(pass, encoded));

        return "loginView";
    }

    @PostMapping
    public String login(@RequestParam(name = "phone") String phoneStr, @RequestParam String password, HttpServletRequest request, RedirectAttributes ra) {
        Integer phone = null;
        try {
            phone = Integer.parseInt(phoneStr);
        } catch (NumberFormatException e) {
        }

        User user = userService.findUserByPhone(phone);
        if (user == null || !user.getPassword().equals(password)) {
            ra.addFlashAttribute("redirectMessage", "Invalid username or passowrd");
            return "redirect:" + LOGIN_URL;
        }
        LoginUtil.loggInnBruker(request, user.getPhone());
        return "redirect:" + PARTICIPANTS_URL;
    }
}
