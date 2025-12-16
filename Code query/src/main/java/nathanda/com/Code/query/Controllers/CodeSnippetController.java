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
import nathanda.com.Code.query.Repositaries.CodeSnippetRepositary;
import nathanda.com.Code.query.Repositaries.CommentRepository;


@Controller
public class CodeSnippetController {

    @Autowired
    private CodeSnippetRepositary snippetRepo;
    @Autowired
    private CommentRepository commentRepo;


    // Show form page
    @GetMapping("/add-snippet")
    public String showAddSnippetForm() {
        return "add-snippet";
    }

    // Handle snippet form submit
    @PostMapping("/snippets")
    public String addSnippet(@RequestParam String title,
                             @RequestParam String language,
                             @RequestParam String snippet) {
        CodeSnippet cs = new CodeSnippet();
        cs.setTitle(title);
        cs.setLanguage(language);
        cs.setSnippet(snippet);
        snippetRepo.save(cs);
        return "redirect:/resources";
    }

    // Display all snippets
    @GetMapping("/resources")
    public String showResources(Model model) {
        List<CodeSnippet> snippets = snippetRepo.findAll(); // fetch all snippets from DB
        model.addAttribute("snippets", snippets); // pass to HTML
        return "resources"; // resolves to resources.html
    }

    @GetMapping("/snippets/{id}")
public String showSnippetDetails(@PathVariable Long id, Model model) {
    CodeSnippet snippet = snippetRepo.findById(id).orElseThrow();
    List<Comment> comments = commentRepo.findBySnippetId(id);
    model.addAttribute("snippet", snippet);
    model.addAttribute("comments", comments);
    return "snippet-details";
}

}

