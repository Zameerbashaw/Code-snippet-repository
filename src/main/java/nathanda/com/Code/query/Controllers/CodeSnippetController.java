package nathanda.com.Code.query.Controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import nathanda.com.Code.query.Models.CodeSnippet;
import nathanda.com.Code.query.Models.Comment;
import nathanda.com.Code.query.Repositaries.CommentRepository; // Keep this for now
import nathanda.com.Code.query.Services.SnippetService;

@Controller
public class CodeSnippetController {

    @Autowired
    private SnippetService snippetService; // USE THIS for Snippets

    @Autowired
    private CommentRepository commentRepo; // USE THIS for Comments

    @GetMapping("/add-snippet")
    public String showAddSnippetForm() {
        return "add-snippet";
    }

    @PostMapping("/snippets")
    public String addSnippet(@RequestParam String title,
                             @RequestParam String language,
                             @RequestParam String snippet) {
        CodeSnippet cs = new CodeSnippet();
        cs.setTitle(title);
        cs.setLanguage(language);
        cs.setSnippet(snippet);
        
        // FIXED: Use Service instead of Repo
        snippetService.saveSnippet(cs); 
        
        return "redirect:/resources";
    }

    @GetMapping("/resources")
    public String showResources(Model model) {
        // FIXED: Use Service (This will now use Redis Cache!)
        List<CodeSnippet> snippets = snippetService.getAllSnippets(); 
        model.addAttribute("snippets", snippets);
        return "resources";
    }

    @GetMapping("/snippets/{id}")
    public String showSnippetDetails(@PathVariable Long id, Model model) {
        // FIXED: Use Service to get ID
        CodeSnippet snippet = snippetService.getSnippetById(id);
        
        // Keep using Repo for comments (since we didn't make a service for them yet)
        List<Comment> comments = commentRepo.findBySnippetId(id);
        
        model.addAttribute("snippet", snippet);
        model.addAttribute("comments", comments);
        return "snippet-details";
    }
}