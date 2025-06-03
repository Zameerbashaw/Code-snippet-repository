package nathanda.com.Code.query.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nathanda.com.Code.query.Models.Users;
import nathanda.com.Code.query.Repositaries.UserRepositary;

@RestController
public class Usercontroller {
  @Autowired 
  private UserRepositary userRepo;

  @PostMapping("/register")
  public String register(@RequestParam String username, @RequestParam String email, @RequestParam String password) {
    Users u = new Users();
    u.setUsername(username);
    u.setEmail(email);
    u.setPassword(password);
    userRepo.save(u);
    return "Registered successfully";
  }

  @PostMapping("/login")
  public String login(@RequestParam String user, @RequestParam String password) {
    Users existing = userRepo.findByUsername(user);
    if (existing != null && existing.getPassword().equals(password)) {
      return "Login successful";
    } else {
      return "Invalid credentials";
    }
  }
}
