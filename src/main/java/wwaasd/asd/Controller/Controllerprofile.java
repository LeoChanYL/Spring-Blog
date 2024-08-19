package wwaasd.asd.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller
public class Controllerprofile {

        @Autowired
        private JdbcTemplate jdbcTemplate;

        @GetMapping("/profile/{userId}")
        public String showProfile(@PathVariable("userId") String userId, Model model) {
            // Retrieve the user's information from the database

            Map<String, Object> userData = jdbcTemplate.queryForMap("SELECT * FROM USERS1 WHERE NAME = ?", userId);
            // Retrieve the user's photos from the database
            List<Map<String, Object>> photos = jdbcTemplate.queryForList("SELECT * FROM Name1 WHERE USER_ID = ? ORDER BY CREATED_AT DESC", userId);

            // Retrieve the user's comments from the database
            List<Map<String, Object>> comments = jdbcTemplate.queryForList("SELECT * FROM COMMENTS WHERE UID = ? ORDER BY CREATED_AT DESC", userId);

            for (Map<String, Object> photo : photos) {
                byte[] imageData = (byte[]) photo.get("IMAGE");
                String imageDataString = Base64.getEncoder().encodeToString(imageData);
                photo.replace("IMAGE", imageDataString);
            }
            String name = "anonymousUser";
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (!Objects.equals(authentication.getName(), "anonymousUser") && authentication.isAuthenticated()){
                name = authentication.getName();
            }

            // Add the user's information, photos, and comments to the model for display on the page
            model.addAttribute("user", userData);
            model.addAttribute("photos", photos);
            model.addAttribute("comments", comments);
            model.addAttribute("cuid",name);

            // Return the name of the JSP page to display
            return "profile";
        }




        @PostMapping("/profile/update/{userId}")
        public String saveProfile(@PathVariable("userId") String userId, @RequestParam("description") String description) {
            // Update the user's description in the database
            jdbcTemplate.update("UPDATE USERS1 SET DESCRIPTION = ? WHERE NAME = ?", description, userId);

            // Redirect to the user's profile page
            return "redirect:/profile/" + userId;
        }

    }


