package co.edu.uco.arquisw.infraestructura.seguridad.configuracion;

import co.edu.uco.arquisw.infraestructura.seguridad.constante.SecurityConstants;
import co.edu.uco.arquisw.infraestructura.seguridad.filtro.JWTTokenValidatorFilter;
import co.edu.uco.arquisw.infraestructura.seguridad.filtro.RequestValidationBeforeFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableWebSecurity
public class ProjectSecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .cors().configurationSource(request -> {
                    CorsConfiguration config = new CorsConfiguration();
                    config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
                    config.setAllowedMethods(Collections.singletonList("*"));
                    config.setAllowCredentials(true);
                    config.setAllowedHeaders(Collections.singletonList("*"));
                    config.setExposedHeaders(List.of(SecurityConstants.JWT_HEADER));
                    config.setMaxAge(3600L);
                    return config;
                }).and()
                .csrf().and()
                .addFilterBefore(new RequestValidationBeforeFilter(), BasicAuthenticationFilter.class)
                .addFilterBefore(new JWTTokenValidatorFilter(), BasicAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/login").authenticated()
                .antMatchers(HttpMethod.POST, "/fases").permitAll()
                .antMatchers("/**").authenticated()
                .and().httpBasic();

        return http.build();
    }
}