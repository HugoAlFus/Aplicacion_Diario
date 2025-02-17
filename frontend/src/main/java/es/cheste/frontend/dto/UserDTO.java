package es.cheste.frontend.dto;

import java.util.Objects;

/**
 * Data Transfer Object (DTO) para los usuarios.
 *
 * @author Hugo Almodóvar Fuster
 * @version 1.0
 */
public class UserDTO {

    private String username;
    private String email;

    /**
     * Constructor por defecto.
     */
    public UserDTO() {
        super();
    }

    /**
     * Constructor con todos los campos.
     *
     * @param username el nombre de usuario.
     * @param email    el correo electrónico del usuario.
     */
    public UserDTO(String username, String email) {
        this.username = username;
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return Objects.equals(username, userDTO.username) && Objects.equals(email, userDTO.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, email);
    }

    /**
     * Obtiene el nombre de usuario.
     *
     * @return el nombre de usuario.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Establece el nombre de usuario.
     *
     * @param username el nombre de usuario.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Obtiene el correo electrónico del usuario.
     *
     * @return el correo electrónico del usuario.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Establece el correo electrónico del usuario.
     *
     * @param email el correo electrónico del usuario.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserDTO{");
        sb.append("username='").append(username).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append('}');
        return sb.toString();
    }
}