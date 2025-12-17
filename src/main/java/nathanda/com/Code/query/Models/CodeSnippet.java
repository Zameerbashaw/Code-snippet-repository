package nathanda.com.Code.query.Models;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CodeSnippet implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String title;
  private String language;

  public CodeSnippet() {}

  public Long getId() {
    return id;
}
  
  
  public String getTitle() {
    return title;
}

  public void setTitle(String title) {
    this.title = title;
  }

 

  public String getLanguage() {
    return language;
}

  public void setLanguage(String language) {
    this.language = language;
  }

  @Column(length = 5000)
  private String snippet;

  public String getSnippet() {
    return snippet;
}

public void setSnippet(String snippet) {
    this.snippet = snippet;
}
}

