package es.cheste.backend.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "DIARY_ENTRY")
public class DiaryEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(name = "TEXT")
    private String content;

    @Column(name = "CREATED_AT")
    private LocalDate createdAt;

    @ElementCollection
    private List<String> filePaths;

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    public DiaryEntry() {
        super();
    }

    public DiaryEntry(String title, String content, List<String> filePaths, User user) {
        this.title = title;
        this.createdAt = LocalDate.now();
        this.content = content;
        this.filePaths = filePaths;
        this.user = user;
    }

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

    public Long getId() {
        return this.id;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<String> getFilePaths() {
        return filePaths;
    }

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
        sb.append(", user=").append(user);
        sb.append('}');
        return sb.toString();
    }
}
