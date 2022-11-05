package no.dat108.oblig4.controller;

import no.dat108.oblig4.model.User;
import no.dat108.oblig4.model.UserService;
import no.dat108.oblig4.utils.LoginUtil;
import no.dat108.oblig4.utils.ValidatorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/${app.url.registration}")
public class RegisterController {
    @Value("${app.url.participants}")
    private String PARTICIPANTS_URL;

    @Value("${app.url.registration}")
    private String REGISTRATION_URL;

    @Autowired
    private UserService userService;

    @GetMapping
    public String showForm(HttpSession session) {
        if (LoginUtil.erBrukerInnlogget(session)) {
            return "redirect:" + PARTICIPANTS_URL;
        }

        return "registrationView";
    }

    @PostMapping
    public String register(@RequestParam(name = "first-name") String firstName, @RequestParam(name = "last-name") String lastName, @RequestParam(name = "phone") String phoneStr, @RequestParam String password, @RequestParam(name = "repeat-password") String repeatPassword, @RequestParam String gender, HttpSession session, HttpServletRequest request, Model model, RedirectAttributes ra) {
        if (LoginUtil.erBrukerInnlogget(session)) {
            return "redirect:" + PARTICIPANTS_URL;
        }

        boolean error = false;
        Integer phone = null;
        try {
            phone = Integer.parseInt(phoneStr);
        } catch (NumberFormatException e) {
            ra.addFlashAttribute("phoneError", "Invalid phone-number");
            error = true;
        }

        // Check if user exists
        boolean userExists = userService.findUserByPhone(phone) != null;

        // User already exists
        if (userExists) {
            ra.addFlashAttribute("phoneError", "This phone number is already registered");
            error = true;
        }

        // Password is invalid
        if (!ValidatorUtil.password(password)) {
            ra.addFlashAttribute("passwordError", "Invalid password");
            error = true;
        } else if (!ValidatorUtil.passowrdsMatch(password, repeatPassword)) {
            System.out.println("passowrds don't match!");
            ra.addFlashAttribute("passwordError", "Passwords don't match");
            error = true;
        }

        // first name is invalid
        firstName = firstName.trim();
        if (!ValidatorUtil.firstName(firstName)) {
            ra.addFlashAttribute("firstNameError", "Invalid first name");
            error = true;
        }

        // last name is invalid
        if (!ValidatorUtil.lastName(lastName)) {
            ra.addFlashAttribute("lastNameError", "Invalid last name");
            error = true;
        }

        // phone is invalid
        if (!ValidatorUtil.phone(phoneStr)) {
            ra.addFlashAttribute("genderError", "Invalid phone number");
            error = true;
        }

        // gender is invalid
        if (!ValidatorUtil.gender(gender)) {
            ra.addFlashAttribute("genderError", "Invalid gender");
            error = true;
        }

        // check if an error was registered
        if (!error) {
            User user = userService.addUser(phone, firstName, lastName, gender, password);
            LoginUtil.loggInnBruker(request, phone);
            model.addAttribute("user", user);
            return "participantView";
        }


        return "redirect:" + REGISTRATION_URL;

    }
}
