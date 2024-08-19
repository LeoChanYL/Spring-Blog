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

import java.util.*;

@Controller
public class ControllShowDetail {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("/showDetail/{id}")
    public String showDetail(@PathVariable("id") Long id, Model model) {
        // Retrieve the image for the given ID from the database
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        String role =authentication.getAuthorities().toString();


        Map<String, Object> img = jdbcTemplate.queryForMap(
                "SELECT * FROM Name1 WHERE ID=?", id);
        byte[] imageData = (byte[]) img.get("IMAGE");
        String imageDataString = Base64.getEncoder().encodeToString(imageData);
        img.replace("IMAGE", imageDataString);
//        byte[] imageData = jdbcTemplate.queryForObject(
//                "SELECT IMAGE FROM NAME1 WHERE ID=?", new Object[]{id}, byte[].class);
//        List<String> comments=jdbcTemplate.queryForList(
//                "SELECT Comment FROM COMMENTs WHERE PID=? order by Created_at desc ",new Object[]{id},String.class);
//        List<String>commenter=jdbcTemplate.queryForList(
//                "SELECT UID FROM COMMENTs WHERE PID=? order by Created_at desc ",new Object[]{id},String.class);
//        List<String>commentTime=jdbcTemplate.queryForList(
//                "SELECT Created_at FROM COMMENTs WHERE PID=? order by Created_at desc ",new Object[]{id},String.class);

        List<Map<String, Object>> comments= jdbcTemplate.queryForList(
                "SELECT * FROM COMMENTs WHERE PID=? order by Created_at desc ",new Object[]{id});

//        List<Map<String, String>> commentList = new ArrayList<>();
//        for (int i = 0; i < comments.size(); i++) {
//            Map<String,String> map=new HashMap<>();
//            map.put("Time",commentTime.get(i));
//            map.put("Commter",commenter.get(i));
//            map.put("Comment", comments.get(i));
//            commentList.add(map);
//        }
        // Encode the image data as a Base64 string


        // Add the image data string to the model for display on the page
        model.addAttribute("img", img);
        model.addAttribute("Comments", comments);
        model.addAttribute("Iid",id);
        model.addAttribute("cuser",name);



        return "detail";
    }



    @PostMapping("/Comment/{id}")
    public String addComment(@PathVariable("id") Long id, @RequestParam("comment") String comment) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        jdbcTemplate.update("INSERT INTO COMMENTs (PID,UID,Comment) VALUES (?,?,?)",id,name,comment);
        return "redirect:/showDetail/{id}";
    }

}
