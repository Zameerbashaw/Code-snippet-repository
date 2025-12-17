package nathanda.com.Code.query.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import nathanda.com.Code.query.Models.CodeSnippet;
import nathanda.com.Code.query.Repositaries.CodeSnippetRepositary;

@Service
public class SnippetService {

    @Autowired
    private CodeSnippetRepositary snippetRepo;

    // 1. Get All (Cached)
    @Cacheable("allSnippets")
    public List<CodeSnippet> getAllSnippets() {
        return snippetRepo.findAll();
    }

    // 2. Save (Clears Cache)
    @CacheEvict(value = "allSnippets", allEntries = true)
    public void saveSnippet(CodeSnippet snippet) {
        snippetRepo.save(snippet);
    }

    // 3. Get One by ID (New Method)
    // We usually don't cache individual items yet to keep it simple
    public CodeSnippet getSnippetById(Long id) {
        return snippetRepo.findById(id).orElseThrow(() -> new RuntimeException("Snippet not found"));
    }
}