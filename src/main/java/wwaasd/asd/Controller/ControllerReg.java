package wwaasd.asd.Controller;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import wwaasd.asd.Model.User;
import wwaasd.asd.Service.UserService;

@Controller
public class ControllerReg {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("/signup")
    public String SignUp(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @GetMapping("/login")
    public String Login(Model model) {
        return "login";
    }

    @PostMapping("/signup")
    public String pSignUp(@ModelAttribute("user") User user, BindingResult result) {

        if (result.hasErrors()) {
            return "/signup";
        }
        UserService.signupService(jdbcTemplate,user);

        return "redirect:/login";
    }

}




