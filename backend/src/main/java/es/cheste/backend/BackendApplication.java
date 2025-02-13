package es.cheste.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 * Clase principal de la aplicación Spring Boot.
 *
 * @author Hugo Almodóvar Fuster
 * @version 1.0
 */
@SpringBootApplication(proxyBeanMethods = false, scanBasePackages = "es.cheste.backend")
@EntityScan("es.cheste.backend.model")
public class BackendApplication {

    /**
     * Método principal que inicia la aplicación Spring Boot.
     *
     * @param args los argumentos de la línea de comandos.
     */
    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

}