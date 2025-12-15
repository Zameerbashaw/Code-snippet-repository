package nathanda.com.Code.query.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;




// import nathanda.com.Code.query.Repositaries.CodeSnippetRepositary;

@Controller
public class Controllers {

    @Autowired
    // private CodeSnippetRepositary snippetRepo;

    @GetMapping({"/index"})
    public String home() {
        return "index"; // maps to home.html or home.jsp
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }


    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
    @Controller
public class SnippetViewController {
    @GetMapping("/newcode")
    public String showSnippetForm() {
        return "newcode"; 
    }
}

}
