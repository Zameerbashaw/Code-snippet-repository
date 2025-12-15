package nathanda.com.Code.query.Models;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @ManyToOne
    private Users user;

    @ManyToOne
    private CodeSnippet snippet;

    private LocalDateTime postedAt = LocalDateTime.now();

    // Default constructor
    public Comment() {
    }

    // Constructor with parameters
    public Comment(String content, Users user, CodeSnippet snippet) {
        this.content = content;
        this.user = user;
        this.snippet = snippet;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public CodeSnippet getSnippet() {
        return snippet;
    }

    public void setSnippet(CodeSnippet snippet) {
        this.snippet = snippet;
    }

    public LocalDateTime getPostedAt() {
        return postedAt;
    }

    public void setPostedAt(LocalDateTime postedAt) {
        this.postedAt = postedAt;
    }
}
