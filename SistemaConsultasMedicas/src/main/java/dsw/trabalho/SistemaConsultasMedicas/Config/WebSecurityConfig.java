package dsw.trabalho.SistemaConsultasMedicas.Config;

import dsw.trabalho.SistemaConsultasMedicas.Security.UsuarioDetailsServiceImpl;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.*;
import org.springframework.security.authentication.dao.*;
import org.springframework.security.config.annotation.authentication.builders.*;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig{

    @Bean
    public UserDetailsService userDetailsService() {
        return new UsuarioDetailsServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll() // Allow access to common static resources without authentication
                        .requestMatchers("/home").permitAll() // Allow access to /home without authentication
                        .requestMatchers("/admin/**").hasRole("admin") // Require "admin" role for /admin pages
                        .requestMatchers("/medico/**").hasRole("medico")
                        .requestMatchers("/paciente/**").hasRole("paciente")
                        .anyRequest().authenticated()
                )
                .httpBasic(withDefaults());
        return http.build();
    }

}
