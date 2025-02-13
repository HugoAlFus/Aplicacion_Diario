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
    private String oldPassword;
    private String newPassword;

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
     * @param oldPassword la contraseña antigua del usuario.
     * @param newPassword la nueva contraseña del usuario.
     */
    public UserUpdatePasswordDTO(String email, String oldPassword, String newPassword) {
        this.email = email;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
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
    public String getOldPassword() {
        return oldPassword;
    }

    /**
     * Establece la contraseña antigua del usuario.
     *
     * @param oldPassword la contraseña antigua del usuario.
     */
    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    /**
     * Obtiene la nueva contraseña del usuario.
     *
     * @return la nueva contraseña del usuario.
     */
    public String getNewPassword() {
        return newPassword;
    }

    /**
     * Establece la nueva contraseña del usuario.
     *
     * @param newPassword la nueva contraseña del usuario.
     */
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserUpdatePasswordDTO{");
        sb.append("email='").append(email).append('\'');
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