package no.dat108.oblig4.controller;

import no.dat108.oblig4.model.User;
import no.dat108.oblig4.model.UserService;
import no.dat108.oblig4.utils.LoginUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/${app.url.participants}")
public class ParticipantsController {
    @Value("${app.url.login}")
    private String LOGIN_URL;

    @Autowired
    private UserService userService;

    @GetMapping
    public String showParticipants(HttpSession session, Model model, RedirectAttributes ra) {
        if (!LoginUtil.erBrukerInnlogget(session)) {
            ra.addFlashAttribute("redirectMessage", "You need to be logged in to view this page");
            return "redirect:" + LOGIN_URL;
        }

        Integer phone = (Integer) session.getAttribute("phone");

        User user = userService.findUserByPhone(phone);
        List<User> users = userService.findAllSorted();

        if (user != null) {
            model.addAttribute("userPhone", user.getPhone());
            model.addAttribute("userName", user.getName());

            model.addAttribute("users", users);

            return "participantsView";
        }


        ra.addFlashAttribute("redirectMessage", "The current user no longer exist");
        LoginUtil.loggUtBruker(session);
        return "redirect:" + LOGIN_URL;
    }

    @PostMapping
    public String logOut(HttpSession session) {
        LoginUtil.loggUtBruker(session);

        return "redirect:" + LOGIN_URL;
    }
}
