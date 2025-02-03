package es.cheste.frontend.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class DiaryEntryModel {

    private Long id;
    private String title;
    private String content;
    private LocalDate createdAt;
    private List<String> filePaths;

    public DiaryEntryModel() {
        super();
    }

    public DiaryEntryModel(Long id, String title, String content, LocalDate createdAt, List<String> filePaths) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.filePaths = filePaths;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiaryEntryModel that = (DiaryEntryModel) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
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
}
