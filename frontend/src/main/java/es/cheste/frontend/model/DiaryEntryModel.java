package es.cheste.frontend.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 * Modelo que representa una entrada de diario.
 *
 * @author Hugo Almodóvar Fuster
 * @version 1.0
 */
public class DiaryEntryModel {

    private Long id;
    private String title;
    private String content;
    private LocalDate createdAt;
    private List<String> filePaths;

    /**
     * Constructor por defecto.
     */
    public DiaryEntryModel() {
        super();
    }

    /**
     * Constructor con parámetros.
     *
     * @param id el ID de la entrada de diario.
     * @param title el título de la entrada de diario.
     * @param content el contenido de la entrada de diario.
     * @param createdAt la fecha de creación de la entrada de diario.
     * @param filePaths la lista de rutas de archivos adjuntos a la entrada de diario.
     */
    public DiaryEntryModel(Long id, String title, String content, LocalDate createdAt, List<String> filePaths) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.filePaths = filePaths;
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
        DiaryEntryModel that = (DiaryEntryModel) o;
        return Objects.equals(id, that.id);
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
     * Obtiene el ID de la entrada de diario.
     *
     * @return el ID de la entrada de diario.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el ID de la entrada de diario.
     *
     * @param id el ID de la entrada de diario.
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
     * Obtiene la lista de rutas de archivos adjuntos a la entrada de diario.
     *
     * @return la lista de rutas de archivos adjuntos.
     */
    public List<String> getFilePaths() {
        return filePaths;
    }

    /**
     * Establece la lista de rutas de archivos adjuntos a la entrada de diario.
     *
     * @param filePaths la lista de rutas de archivos adjuntos.
     */
    public void setFilePaths(List<String> filePaths) {
        this.filePaths = filePaths;
    }
}