package sahil.blogplatform.model;

import java.sql.Timestamp;

public class Comment {
    private int id;
    private int postId;
    private int userId;
    private String content;
    private Timestamp createdAt;

    public Comment() {}

    public Comment(int postId, int userId, String content) {
        this.postId = postId;
        this.userId = userId;
        this.content = content;
    }

    // --- Getters and Setters ---
    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public int getPostId() { return postId; }

    public void setPostId(int postId) { this.postId = postId; }

    public int getUserId() { return userId; }

    public void setUserId(int userId) { this.userId = userId; }

    public String getContent() { return content; }

    public void setContent(String content) { this.content = content; }

    public Timestamp getCreatedAt() { return createdAt; }

    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }
}
