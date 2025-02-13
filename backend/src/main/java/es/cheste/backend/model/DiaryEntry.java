package es.cheste.backend.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 * Entidad que representa una entrada de diario.
 *
 * @author Hugo Almodóvar Fuster
 * @version 1.0
 */
@Entity
@Table(name = "DIARY_ENTRY", uniqueConstraints = {@UniqueConstraint(columnNames = {"CREATED_AT", "USER_ID"})})
public class DiaryEntry {

    /**
     * Identificador único de la entrada de diario.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Título de la entrada de diario.
     */
    private String title;

    /**
     * Contenido de la entrada de diario.
     */
    @Column(name = "TEXT")
    private String content;

    /**
     * Fecha de creación de la entrada de diario.
     */
    @Column(name = "CREATED_AT")
    private LocalDate createdAt;

    /**
     * Lista de rutas de archivos adjuntos a la entrada de diario.
     */
    @ElementCollection
    private List<String> filePaths;

    /**
     * Usuario al que pertenece la entrada de diario.
     */
    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    /**
     * Constructor por defecto.
     */
    public DiaryEntry() {
        super();
    }

    /**
     * Constructor que acepta título, contenido, rutas de archivos y usuario.
     *
     * @param title el título de la entrada de diario.
     * @param content el contenido de la entrada de diario.
     * @param filePaths las rutas de archivos adjuntos.
     * @param user el usuario al que pertenece la entrada de diario.
     */
    public DiaryEntry(String title, String content, List<String> filePaths, User user) {
        this.title = title;
        this.createdAt = LocalDate.now();
        this.content = content;
        this.filePaths = filePaths;
        this.user = user;
    }

    /**
     * Constructor que acepta id, título, contenido, usuario y rutas de archivos.
     *
     * @param id el identificador único de la entrada de diario.
     * @param title el título de la entrada de diario.
     * @param content el contenido de la entrada de diario.
     * @param user el usuario al que pertenece la entrada de diario.
     * @param filePaths las rutas de archivos adjuntos.
     */
    public DiaryEntry(Long id, String title, String content, User user, List<String> filePaths) {
        this(title, content, filePaths, user);
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiaryEntry that = (DiaryEntry) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    /**
     * Obtiene el identificador único de la entrada de diario.
     *
     * @return el identificador único de la entrada de diario.
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Establece el identificador único de la entrada de diario.
     *
     * @param id el identificador único de la entrada de diario.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el título de la entrada de diario.
     *
     * @return el título de la entrada de diario.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Establece el título de la entrada de diario.
     *
     * @param title el título de la entrada de diario.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Obtiene el contenido de la entrada de diario.
     *
     * @return el contenido de la entrada de diario.
     */
    public String getContent() {
        return content;
    }

    /**
     * Establece el contenido de la entrada de diario.
     *
     * @param content el contenido de la entrada de diario.
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Obtiene la fecha de creación de la entrada de diario.
     *
     * @return la fecha de creación de la entrada de diario.
     */
    public LocalDate getCreatedAt() {
        return createdAt;
    }

    /**
     * Establece la fecha de creación de la entrada de diario.
     *
     * @param createdAt la fecha de creación de la entrada de diario.
     */
    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Obtiene el usuario al que pertenece la entrada de diario.
     *
     * @return el usuario al que pertenece la entrada de diario.
     */
    public User getUser() {
        return user;
    }

    /**
     * Establece el usuario al que pertenece la entrada de diario.
     *
     * @param user el usuario al que pertenece la entrada de diario.
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Obtiene las rutas de archivos adjuntos a la entrada de diario.
     *
     * @return las rutas de archivos adjuntos a la entrada de diario.
     */
    public List<String> getFilePaths() {
        return filePaths;
    }

    /**
     * Establece las rutas de archivos adjuntos a la entrada de diario.
     *
     * @param filePaths las rutas de archivos adjuntos a la entrada de diario.
     */
    public void setFilePaths(List<String> filePaths) {
        this.filePaths = filePaths;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DiaryEntry{");
        sb.append("id=").append(id);
        sb.append(", title='").append(title).append('\'');
        sb.append(", content='").append(content).append('\'');
        sb.append(", createdAt=").append(createdAt);
        sb.append(", filePaths=").append(filePaths);
        sb.append('}');
        return sb.toString();
    }
}