package es.cheste.backend.dto;

import java.util.Objects;

/**
 * Data Transfer Object (DTO) para la actualización de la contraseña del usuario.
 *
 * @author Hugo Almodóvar Fuster
 * @version 1.0
 */
public class UserUpdatePasswordDTO {

    private String email;
    private String password;
    private String confirmPassword;

    /**
     * Constructor por defecto.
     */
    public UserUpdatePasswordDTO() {
        super();
    }

    /**
     * Constructor con todos los campos.
     *
     * @param email       el correo electrónico del usuario.
     * @param password la contraseña antigua del usuario.
     * @param confirmPassword la nueva contraseña del usuario.
     */
    public UserUpdatePasswordDTO(String email, String password, String confirmPassword) {
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(email);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserUpdatePasswordDTO that = (UserUpdatePasswordDTO) o;
        return Objects.equals(email, that.email);
    }

    /**
     * Obtiene la contraseña antigua del usuario.
     *
     * @return la contraseña antigua del usuario.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Establece la contraseña antigua del usuario.
     *
     * @param password la contraseña antigua del usuario.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Obtiene la nueva contraseña del usuario.
     *
     * @return la nueva contraseña del usuario.
     */
    public String getConfirmPassword() {
        return confirmPassword;
    }

    /**
     * Establece la nueva contraseña del usuario.
     *
     * @param confirmPassword la nueva contraseña del usuario.
     */
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserUpdatePasswordDTO{");
        sb.append("email='").append(email).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", confirmPassword='").append(confirmPassword).append('\'');
        sb.append('}');
        return sb.toString();
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
}