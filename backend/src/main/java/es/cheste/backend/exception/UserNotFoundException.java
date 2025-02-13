package es.cheste.backend.exception;

/**
 * Excepción lanzada cuando no se encuentra el usuario.
 *
 * @author Hugo Almodóvar Fuster
 * @version 1.0
 */
public class UserNotFoundException extends RuntimeException {

    /**
     * Constructor que acepta un mensaje de error.
     *
     * @param message el mensaje de error.
     */
    public UserNotFoundException(String message) {
        super(message);
    }
}