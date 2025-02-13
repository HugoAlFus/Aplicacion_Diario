package es.cheste.backend.exception;

/**
 * Excepción lanzada cuando no se encuentra una entrada.
 *
 * @author Hugo Almodóvar Fuster
 * @version 1.0
 */
public class EntryNotFoundException extends RuntimeException {

    /**
     * Constructor que acepta un mensaje de error.
     *
     * @param message el mensaje de error.
     */
    public EntryNotFoundException(String message) {
        super(message);
    }
}