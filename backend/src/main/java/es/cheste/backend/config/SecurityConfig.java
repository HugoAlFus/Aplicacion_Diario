package es.cheste.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Clase de configuración de seguridad para la aplicación.
 * <p>
 * Esta clase configura la seguridad de la aplicación utilizando Spring Security.
 * </p>
 *
 * @author Hugo Almodóvar Fuster
 * @version 1.0
 */
@Configuration(proxyBeanMethods = false)
public class SecurityConfig {

    /**
     * Define un bean para el codificador de contraseñas.
     *
     * @return una instancia de BCryptPasswordEncoder.
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * Configura la cadena de filtros de seguridad.
     *
     * @param http el objeto HttpSecurity para configurar la seguridad web.
     * @return la cadena de filtros de seguridad configurada.
     * @throws Exception si ocurre un error durante la configuración.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/api/users/register", "/api/users", "/api/users/{userId}", "/api/users/{username}/id" , "/api/users/login",
                                "/api/entries","/api/entries/by-date", "/api/entries/{entryId}").permitAll()
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}