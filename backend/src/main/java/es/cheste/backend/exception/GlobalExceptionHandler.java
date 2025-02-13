package es.cheste.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Controlador de asesoramiento para manejar excepciones globales en la aplicación.
 *
 * @author Hugo Almodóvar Fuster
 * @version 1.0
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Maneja la excepción EntryNotFoundException.
     *
     * @param ex la excepción EntryNotFoundException.
     * @return una respuesta HTTP con el mensaje de error y el estado NOT_FOUND.
     */
    @ExceptionHandler(EntryNotFoundException.class)
    public ResponseEntity<String> handleEntryNotFoundException(EntryNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    /**
     * Maneja la excepción InvalidCredentialsException.
     *
     * @param ex la excepción InvalidCredentialsException.
     * @return una respuesta HTTP con el mensaje de error y el estado NOT_FOUND.
     */
    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<String> handleInvalidCredentialsException(InvalidCredentialsException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    /**
     * Maneja la excepción PermissionDeniedException.
     *
     * @param ex la excepción PermissionDeniedException.
     * @return una respuesta HTTP con el mensaje de error y el estado UNAUTHORIZED.
     */
    @ExceptionHandler(PermissionDeniedException.class)
    public ResponseEntity<String> handlePermissionDeniedException(PermissionDeniedException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    /**
     * Maneja la excepción UserAlreadyExistsException.
     *
     * @param ex la excepción UserAlreadyExistsException.
     * @return una respuesta HTTP con el mensaje de error y el estado CONFLICT.
     */
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<String> handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    /**
     * Maneja la excepción UserNotFoundException.
     *
     * @param ex la excepción UserNotFoundException.
     * @return una respuesta HTTP con el mensaje de error y el estado NOT_FOUND.
     */
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}