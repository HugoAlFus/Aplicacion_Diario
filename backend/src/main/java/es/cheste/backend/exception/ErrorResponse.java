package es.cheste.backend.exception;

import java.time.LocalDateTime;

/**
 * Clase que representa una respuesta de error.
 *
 * @author Hugo Almodóvar Fuster
 * @version 1.0
 */
public class ErrorResponse {

    private String message;
    private int status;
    private LocalDateTime time;

    /**
     * Constructor que inicializa el mensaje de error y el estado.
     *
     * @param message el mensaje de error.
     * @param status  el estado del error.
     */
    public ErrorResponse(String message, int status) {
        this.message = message;
        this.status = status;
        this.time = LocalDateTime.now();
    }

    /**
     * Obtiene el mensaje de error.
     *
     * @return el mensaje de error.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Establece el mensaje de error.
     *
     * @param message el mensaje de error.
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Obtiene el estado del error.
     *
     * @return el estado del error.
     */
    public int getStatus() {
        return status;
    }

    /**
     * Establece el estado del error.
     *
     * @param status el estado del error.
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * Obtiene el tiempo en que ocurrió el error.
     *
     * @return el tiempo en que ocurrió el error.
     */
    public LocalDateTime getTime() {
        return time;
    }

    /**
     * Establece el tiempo en que ocurrió el error.
     *
     * @param time el tiempo en que ocurrió el error.
     */
    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ErrorResponse{");
        sb.append("message='").append(message).append('\'');
        sb.append(", status=").append(status);
        sb.append(", time=").append(time);
        sb.append('}');
        return sb.toString();
    }
}