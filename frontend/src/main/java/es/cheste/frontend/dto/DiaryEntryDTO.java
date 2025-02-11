package es.cheste.frontend.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class DiaryEntryDTO {

    private Long id;
    private String title;
    private String content;
    private LocalDate createdAt;
    private List<String> filePaths;
    private Long userId;

    public DiaryEntryDTO() {
        super();
    }

    public DiaryEntryDTO(Long id, String title, String content, LocalDate createdAt, List<String> filePaths, Long userId) {
        this(createdAt, content, title, filePaths, userId);
        this.id = id;
    }

    public DiaryEntryDTO(LocalDate createdAt, String content, String title, List<String> filePaths, Long userId) {
        this.createdAt = createdAt;
        this.content = content;
        this.title = title;
        this.filePaths = filePaths;
        this.userId = userId;
    }

    public DiaryEntryDTO(String title, String content, LocalDate createdAt, List<String> filePaths, Long id) {
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.filePaths = filePaths;
        this.id = id;
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
        sb.append('}');
        return sb.toString();
    }
}
