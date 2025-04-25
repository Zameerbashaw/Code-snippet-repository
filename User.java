package bajaj.com.finserv.model;

import java.util.List;

public class User {
    private int id;
    private List<Integer> follows;

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public List<Integer> getFollows() { return follows; }
    public void setFollows(List<Integer> follows) { this.follows = follows; }
}
