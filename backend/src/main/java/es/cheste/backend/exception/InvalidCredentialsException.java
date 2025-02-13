package es.cheste.backend.exception;

/**
 * Excepción lanzada cuando las credenciales proporcionadas son inválidas.
 *
 * @author Hugo Almodóvar Fuster
 * @version 1.0
 */
public class InvalidCredentialsException extends RuntimeException {

    /**
     * Constructor que acepta un mensaje de error.
     *
     * @param message el mensaje de error.
     */
    public InvalidCredentialsException(String message) {
        super(message);
    }
}