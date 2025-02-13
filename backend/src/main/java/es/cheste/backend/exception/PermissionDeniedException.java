package es.cheste.backend.exception;

/**
 * Excepción lanzada cuando se deniega el permiso.
 *
 * @author Hugo Almodóvar Fuster
 * @version 1.0
 */
public class PermissionDeniedException extends RuntimeException {

    /**
     * Constructor que acepta un mensaje de error.
     *
     * @param message el mensaje de error.
     */
    public PermissionDeniedException(String message) {
        super(message);
    }
}