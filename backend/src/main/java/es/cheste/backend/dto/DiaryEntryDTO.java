package es.cheste.backend.dto;

import java.time.LocalDateTime;
import java.util.Objects;

public class DiaryEntryDTO {

    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;

    public DiaryEntryDTO() {
        super();
    }

    public DiaryEntryDTO(Long id, String title, String content, LocalDateTime createdAt) {
        this(createdAt, content, title);
        this.id = id;
    }

    public DiaryEntryDTO(LocalDateTime createdAt, String content, String title) {
        this.createdAt = createdAt;
        this.content = content;
        this.title = title;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DiaryEntryDTO{");
        sb.append("id=").append(id);
        sb.append(", title='").append(title).append('\'');
        sb.append(", content='").append(content).append('\'');
        sb.append(", createdAt=").append(createdAt);
        sb.append('}');
        return sb.toString();
    }
}
