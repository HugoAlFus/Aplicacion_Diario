package es.cheste.backend.exception;

/**
 * Excepción lanzada cuando el usuario ya existe.
 *
 * @author Hugo Almodóvar Fuster
 * @version 1.0
 */
public class UserAlreadyExistsException extends RuntimeException {

    /**
     * Constructor que acepta un mensaje de error.
     *
     * @param message el mensaje de error.
     */
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}