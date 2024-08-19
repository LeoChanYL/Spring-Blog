package wwaasd.asd.Config;

import org.hibernate.boot.archive.scan.internal.NoopEntryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import wwaasd.asd.Config.AppConfig;
import javax.sql.DataSource;

import static org.springframework.security.config.Customizer.withDefaults;





@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails user1 = User.withUsername("keith")
//                .password("{noop}keithpw").roles("ADMIN", "USER").build();
//        UserDetails user2 = User.withUsername("john")
//                .password("{noop}johnpw").roles("USER").build();
//        InMemoryUserDetailsManager userDetailsManager
//                = new InMemoryUserDetailsManager();
//        userDetailsManager.createUser(user1);
//        userDetailsManager.createUser(user2);
//        return userDetailsManager;
//    }




    @Autowired
    @Bean
    public UserDetailsService jdbcUserDetailsManager(DataSource dataSource) {
        String usersByUsernameQuery = "select username, password, enabled from users where username = ?";
        String authoritiesByUsernameQuery = "select username, authority from authorities where username = ?";
        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
        users.setUsersByUsernameQuery(usersByUsernameQuery);
        users.setAuthoritiesByUsernameQuery(authoritiesByUsernameQuery);
        return users;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http)
            throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                      .requestMatchers("/h2-console").hasRole("ADMIN")
                        .requestMatchers("upload/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("Comment/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("admin/**").hasRole("ADMIN")
                        .requestMatchers("delete/**").hasRole("ADMIN")
                        .requestMatchers("setAdmin/**").hasRole("ADMIN")
                        .anyRequest().permitAll()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .failureUrl("/login?error")
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .permitAll()
                )
                .logout( logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll())
                .httpBasic(withDefaults())
                .headers().frameOptions().sameOrigin();
                http.csrf().disable();

        return http.build();
    }


}

