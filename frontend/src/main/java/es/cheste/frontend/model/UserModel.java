package es.cheste.frontend.model;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Modelo que representa un usuario.
 *
 * @author Hugo Almodóvar Fuster
 * @version 1.0
 */
public class UserModel {

    private Long id;
    private String username;
    private String email;
    private LocalDate createdAt;

    /**
     * Constructor por defecto.
     */
    public UserModel() {
        super();
    }

    /**
     * Constructor con parámetros.
     *
     * @param username el nombre de usuario.
     * @param email el correo electrónico del usuario.
     */
    public UserModel(String username, String email) {
        this.username = username;
        this.email = email;
        this.createdAt = LocalDate.now();
    }

    /**
     * Constructor con parámetros.
     *
     * @param id el ID del usuario.
     * @param username el nombre de usuario.
     * @param email el correo electrónico del usuario.
     */
    public UserModel(Long id, String username, String email) {
        this(username, email);
        this.id = id;
    }

    /**
     * Compara este objeto con otro para verificar si son iguales.
     *
     * @param o el objeto a comparar.
     * @return true si los objetos son iguales, false en caso contrario.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserModel userModel = (UserModel) o;
        return Objects.equals(id, userModel.id);
    }

    /**
     * Calcula el código hash de este objeto.
     *
     * @return el código hash.
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    /**
     * Obtiene el ID del usuario.
     *
     * @return el ID del usuario.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el ID del usuario.
     *
     * @param id el ID del usuario.
     */
    public void setId(Long id) {
        this.id = id;
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

    /**
     * Obtiene la fecha de creación del usuario.
     *
     * @return la fecha de creación del usuario.
     */
    public LocalDate getCreatedAt() {
        return createdAt;
    }

    /**
     * Establece la fecha de creación del usuario.
     *
     * @param createdAt la fecha de creación del usuario.
     */
    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Devuelve una representación en cadena del objeto.
     *
     * @return una cadena que representa el objeto.
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserModel{");
        sb.append("id=").append(id);
        sb.append(", username='").append(username).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", createdAt=").append(createdAt);
        sb.append('}');
        return sb.toString();
    }
}