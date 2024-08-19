package wwaasd.asd.Service;

import org.springframework.jdbc.core.JdbcTemplate;
import wwaasd.asd.Model.User;



public class UserService {

    public static void signupService(JdbcTemplate jdbcTemplate, User user) {



        String query = "INSERT INTO USERS1 (name,  email, phone, description)VALUES (?, ?, ?, ?)";
        String query2 = "Insert into users (username, password, enabled) values (?, ?, ?)";
        String query3 = "INSERT INTO AUTHORITIES (username, authority)VALUES (?, ?)";
        jdbcTemplate.update(query,
                user.getName(),
                user.getEmail(),
                user.getPhone(),

                user.getDescription()
        );
        jdbcTemplate.update(query2,
                user.getName(),
                user.getPassword(),
                true
        );
        jdbcTemplate.update(query3,
                user.getName(),
                "ROLE_USER"
        );


    }





}
