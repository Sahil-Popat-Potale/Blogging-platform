package sahil.blogplatform.model;

import java.sql.Timestamp;

public class Post {
    private int id;
    private String title;
    private String content;
    private int authorId;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public Post(){}

    public Post(String title, String content, int authorId) {
        this.title = title;
        this.content = content;
        this.authorId = authorId;
    }

    // --- Getters and Setters ---
    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }

    public void setContent(String content) { this.content = content; }

    public int getAuthorId() { return authorId; }

    public void setAuthorId(int authorId) { this.authorId = authorId; }

    public Timestamp getCreatedAt() { return createdAt; }

    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }

    public Timestamp getUpdatedAt() { return updatedAt; }

    public void setUpdatedAt(Timestamp updatedAt) { this.updatedAt = updatedAt; }
}
