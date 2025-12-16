package nathanda.com.Code.query.Repositaries;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import nathanda.com.Code.query.Models.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findBySnippetId(Long id);
    // You can add custom query methods if needed, for example:
    // List<Comment> findBySnippetId(Long snippetId);
}
