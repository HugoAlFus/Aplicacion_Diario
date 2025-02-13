package es.cheste.backend.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 * Entidad que representa un usuario en la aplicación.
 *
 * @author Hugo Almodóvar Fuster
 * @version 1.0
 */
@Entity
@Table(name = "user_app")
public class User {

    /**
     * Identificador único del usuario.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nombre de usuario único.
     */
    @Column(unique = true, nullable = false)
    private String username;

    /**
     * Contraseña del usuario.
     */
    @Column(nullable = false)
    private String password;

    /**
     * Correo electrónico único del usuario.
     */
    @Column(unique = true, nullable = false)
    private String email;

    /**
     * Fecha de creación del usuario.
     */
    @Column(name = "CREATED_AT")
    private LocalDate createdAt;

    /**
     * Lista de entradas de diario asociadas al usuario.
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DiaryEntry> diaryEntries;

    /**
     * Constructor por defecto.
     */
    public User() {
        super();
    }

    /**
     * Constructor que acepta id, nombre de usuario, contraseña y correo electrónico.
     *
     * @param id el identificador único del usuario.
     * @param username el nombre de usuario.
     * @param password la contraseña del usuario.
     * @param email el correo electrónico del usuario.
     */
    public User(Long id, String username, String password, String email) {
        this(username, password, email);
        this.id = id;
    }

    /**
     * Constructor que acepta nombre de usuario, contraseña y correo electrónico.
     *
     * @param username el nombre de usuario.
     * @param password la contraseña del usuario.
     * @param email    el correo electrónico del usuario.
     */
    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.createdAt = LocalDate.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    /**
     * Obtiene el identificador único del usuario.
     *
     * @return el identificador único del usuario.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador único del usuario.
     *
     * @param id el identificador único del usuario.
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
     * Obtiene la contraseña del usuario.
     *
     * @return la contraseña del usuario.
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Establece la contraseña del usuario.
     *
     * @param password la contraseña del usuario.
     */
    public void setPassword(String password) {
        this.password = password;
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
     * Obtiene la lista de entradas de diario asociadas al usuario.
     *
     * @return la lista de entradas de diario asociadas al usuario.
     */
    public List<DiaryEntry> getDiaryEntries() {
        return diaryEntries;
    }

    /**
     * Establece la lista de entradas de diario asociadas al usuario.
     *
     * @param diaryEntries la lista de entradas de diario asociadas al usuario.
     */
    public void setDiaryEntries(List<DiaryEntry> diaryEntries) {
        this.diaryEntries = diaryEntries;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("id=").append(id);
        sb.append(", username='").append(username).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", createdAt=").append(createdAt);
        sb.append('}');
        return sb.toString();
    }
}