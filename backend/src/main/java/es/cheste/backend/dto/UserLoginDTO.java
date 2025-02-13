package es.cheste.backend.dto;

import java.util.Objects;

/**
 * Data Transfer Object (DTO) para el inicio de sesión de usuario.
 *
 * @author Hugo Almodóvar Fuster
 * @version 1.0
 */
public class UserLoginDTO implements IUserDTO {

    private String username;
    private String password;

    /**
     * Constructor por defecto.
     */
    public UserLoginDTO() {
        super();
    }

    /**
     * Constructor con todos los campos.
     *
     * @param username el nombre de usuario.
     * @param password la contraseña del usuario.
     */
    public UserLoginDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserLoginDTO that = (UserLoginDTO) o;
        return Objects.equals(username, that.username) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password);
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
     * Obtiene la contraseña del usuario.
     *
     * @return la contraseña del usuario.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Establece la contraseña del usuario.
     *
     * @param password la contraseña del usuario.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserLoginDTO{");
        sb.append("username='").append(username).append('\'');
        sb.append('}');
        return sb.toString();
    }
}