package nathanda.com.Code.query.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
// import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import nathanda.com.Code.query.Models.CodeSnippet;
import nathanda.com.Code.query.Models.Comment;
import nathanda.com.Code.query.Models.Users;
import nathanda.com.Code.query.Repositaries.CodeSnippetRepositary;
import nathanda.com.Code.query.Repositaries.CommentRepository;
import nathanda.com.Code.query.Repositaries.UserRepositary;

@Controller
public class Commentcontrol {

    @Autowired
    private CommentRepository commentRepo;

    @Autowired
    private CodeSnippetRepositary snippetRepo;

    @Autowired
    private UserRepositary userRepo;

    @PostMapping("/snippets/{id}/comments")
    public String postComment(@PathVariable Long id, @RequestParam String content, Model model) {
        // Fetch the snippet to associate the comment with
        CodeSnippet snippet = snippetRepo.findById(id).orElseThrow();

        // Fetch the current user (for simplicity, assuming there's a logged-in user)
        Users currentUser = userRepo.findByUsername("user1"); // Replace with actual user fetching logic

        // Create a new Comment
        Comment comment = new Comment(content, currentUser, snippet);

        // Save the comment
        commentRepo.save(comment);

        // Redirect back to the snippet details page
        return "redirect:/snippets/" + id;
    }
}
