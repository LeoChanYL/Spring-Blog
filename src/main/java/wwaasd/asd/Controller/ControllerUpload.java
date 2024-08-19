package wwaasd.asd.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import java.sql.Timestamp;
import java.util.Arrays;

@Controller
public class ControllerUpload {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("/upload")
    public String upload() {
        return "upload";
    }



    @PostMapping("/upload")
    public String pUpload(@RequestParam("image") MultipartFile file,
                          @RequestParam("description") String name) {


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String user_id = authentication.getName();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        System.out.println(name);
        System.out.println(timestamp);
        //file to blob
        byte[] img = null;
        try {
            img = file.getBytes();
        } catch (Exception e) {
            e.printStackTrace();
        }

        String sql = "insert into Name1 (Name,Image,Created_at,user_id) values (?,?,?,?)";
        jdbcTemplate.update(sql,
                name,
                img,
                timestamp,
                user_id);
        return "redirect:/";
    }


}
