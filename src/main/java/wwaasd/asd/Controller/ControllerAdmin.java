package wwaasd.asd.Controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Map;

@Controller
public class ControllerAdmin {

    @Autowired
    JdbcTemplate jdbcTemplate;


    @GetMapping("/admin")
    public String admin(Model model) {
        List<Map<String, Object>> uids=jdbcTemplate.queryForList("SELECT * FROM USERS1");

        for (Map<String, Object> uid : uids) {
            //gets user authority from database by comparing username
            List<Map<String, Object>> roles = jdbcTemplate.queryForList("SELECT AUTHORITY FROM AUTHORITIES WHERE USERNAME = ?", uid.get("NAME"));
            uid.put("roles",roles);
        }
        model.addAttribute("users",uids);

        return "admin";
    }



    @PostMapping("/delete/Comment/{id}")
    public String deleteComment(@PathVariable("id") String id, HttpServletRequest request) {
        jdbcTemplate.update("DELETE FROM COMMENTS WHERE ID = ?", id);
        String referer = request.getHeader("Referer");
        return "redirect:"+referer;
    }



    @PostMapping("/delete/Photo/{id}")
    public String deletePhoto(@PathVariable("id") String id, HttpServletRequest request) {

        jdbcTemplate.update("DELETE FROM COMMENTS WHERE PID = ?", id);
        jdbcTemplate.update("DELETE FROM Name1 WHERE ID = ?", id);
        String referer = request.getHeader("Referer");
        return "redirect:/";

    }


    @PostMapping("/delete/User/{id}")
    public String deleteUser(@PathVariable("id") String id, HttpServletRequest request) {


        List<Map<String, Object>> Pids = jdbcTemplate.queryForList("SELECT ID FROM Name1 WHERE USER_ID = ?", id);
        for (Map<String, Object> pid : Pids) {
            jdbcTemplate.update("DELETE FROM COMMENTS WHERE PID = ?", pid.get("PID"));
        }
        jdbcTemplate.update("DELETE FROM COMMENTS WHERE UID = ?", id);
        jdbcTemplate.update("DELETE FROM Name1 WHERE USER_ID = ?", id);
        jdbcTemplate.update("DELETE FROM AUTHORITIES WHERE USERNAME = ?", id);
        jdbcTemplate.update("DELETE FROM USERS WHERE USERNAME = ?", id);
        jdbcTemplate.update("DELETE FROM USERS1 WHERE NAME = ?", id);
        String referer = request.getHeader("Referer");
        if (referer.contains("admin") ){
            return "redirect:/admin";
        }else{
            return "redirect:/";
        }

    }



    @PostMapping("/SetAdmin/{id}")
    public String SetAdmin(@PathVariable("id") String id, HttpServletRequest request) {
        jdbcTemplate.update("UPDATE AUTHORITIES SET AUTHORITY = 'ROLE_ADMIN' WHERE USERNAME = ?", id);
        String referer = request.getHeader("Referer");
        return "redirect:"+referer;
    }

}
