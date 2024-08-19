package wwaasd.asd.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
public class ControllerIndex {


    @Autowired
    JdbcTemplate jdbcTemplate;

    @RequestMapping("/")
    public String index(@RequestParam(value = "name",defaultValue ="Visitor",required = true)String name, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!Objects.equals(authentication.getName(), "anonymousUser") && authentication.isAuthenticated()){
            name = authentication.getName();
        }
        model.addAttribute("name", name);
        // * in H2 database

        List<String > names= jdbcTemplate.queryForList("SELECT NAME FROM NAME1 order by CREATED_AT desc ",String.class);
        List<String > Uids= jdbcTemplate.queryForList("SELECT USER_ID FROM NAME1 order by CREATED_AT desc",String.class);
        List<String > Iids= jdbcTemplate.queryForList("SELECT ID FROM NAME1 order by CREATED_AT desc",String.class);
        List<String > timestamps= jdbcTemplate.queryForList("SELECT CREATED_AT FROM NAME1 order by CREATED_AT desc",String.class);
        //show blob image by List<byte[]> in H2 database
        List<byte[]> images = jdbcTemplate.queryForList("SELECT IMAGE FROM NAME1 order by CREATED_AT desc",byte[].class);
        List<String> imageDataStringList = new ArrayList<>();
        for (byte[] imageData : images) {
            if (imageData == null) {
                imageDataStringList.add(null);
                continue;
            }
            String imageDataString = Base64.getEncoder().encodeToString(imageData);
            imageDataStringList.add(imageDataString);
        }
        //show WEB-INF/jsp/hi.jsp with message
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        ArrayList<HashMap<String, String>> maplist=new ArrayList<>();
        for (int i = 0; i < names.size(); i++) {
            HashMap<String, String> map = new HashMap<>();
            map.put("name",names.get(i));
            map.put("id",Iids.get(i));
            map.put("uid",Uids.get(i));
            map.put("timestamp",timestamps.get(i));
            map.put("image",imageDataStringList.get(i));
            maplist.add(map);
        }

        model.addAttribute("List", maplist);
        return "hi";
    }




//
//    @PostMapping("/reg")
//    public String reg(@ModelAttribute("name")String name){
//        String sql = "SELECT * FROM users";
//        jdbcTemplate.update("INSERT INTO NAME1 (NAME) VALUES (?)",name);
//        return "redirect:/";
//    }





}



