package nathanda.com.Code.query.Repositaries;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import nathanda.com.Code.query.Models.CodeSnippet;

@Repository
public interface CodeSnippetRepositary extends JpaRepository<CodeSnippet, Long> {
}

