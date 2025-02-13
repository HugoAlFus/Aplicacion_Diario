package es.cheste.backend.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 * Data Transfer Object (DTO) para las entradas del diario.
 *
 * @author Hugo Almodóvar Fuster
 * @version 1.0
 */
public class DiaryEntryDTO {

    private Long id;
    private String title;
    private String content;
    private LocalDate createdAt;
    private List<String> filePaths;
    private Long userId;

    /**
     * Constructor por defecto.
     */
    public DiaryEntryDTO() {
        super();
    }

    /**
     * Constructor con todos los campos.
     *
     * @param id        el ID de la entrada.
     * @param title     el título de la entrada.
     * @param content   el contenido de la entrada.
     * @param createdAt la fecha de creación de la entrada.
     * @param filePaths las rutas de los archivos adjuntos a la entrada.
     * @param userId    el ID del usuario que creó la entrada.
     */
    public DiaryEntryDTO(Long id, String title, String content, LocalDate createdAt, List<String> filePaths, Long userId) {
        this(createdAt, content, title, filePaths, userId);
        this.id = id;
    }

    /**
     * Constructor sin el campo ID.
     *
     * @param createdAt la fecha de creación de la entrada.
     * @param content el contenido de la entrada.
     * @param title el título de la entrada.
     * @param filePaths las rutas de los archivos adjuntos a la entrada.
     * @param userId el ID del usuario que creó la entrada.
     */
    public DiaryEntryDTO(LocalDate createdAt, String content, String title, List<String> filePaths, Long userId) {
        this(title, content, createdAt, filePaths);
        this.userId = userId;
    }

    /**
     * Constructor sin el campo userId.
     *
     * @param title     el título de la entrada.
     * @param content   el contenido de la entrada.
     * @param createdAt la fecha de creación de la entrada.
     * @param filePaths las rutas de los archivos adjuntos a la entrada.
     */
    public DiaryEntryDTO(String title, String content, LocalDate createdAt, List<String> filePaths) {
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.filePaths = filePaths;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiaryEntryDTO that = (DiaryEntryDTO) o;
        return Objects.equals(id, that.id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public List<String> getFilePaths() {
        return filePaths;
    }

    public void setFilePaths(List<String> filePaths) {
        this.filePaths = filePaths;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DiaryEntryDTO{");
        sb.append("id=").append(id);
        sb.append(", title='").append(title).append('\'');
        sb.append(", content='").append(content).append('\'');
        sb.append(", createdAt=").append(createdAt);
        sb.append(", filePaths=").append(filePaths);
        sb.append(", userId=").append(userId);
        sb.append('}');
        return sb.toString();
    }
}