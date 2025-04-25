package bajaj.com.finserv.model;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
// import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class WebhookResponse {
    private String accessToken;
    private String webhook;
    private Data data;

    // Getters and Setters
    public String getAccessToken() { return accessToken; }
    public void setAccessToken(String accessToken) { this.accessToken = accessToken; }

    public String getWebhook() { return webhook; }
    public void setWebhook(String webhook) { this.webhook = webhook; }

    public Data getData() { return data; }
    public void setData(Data data) { this.data = data; }

    public static class Data {
        private int n;
        private int findId;

        @JsonDeserialize(using = UserListDeserializer.class)
        private List<User> users;

        public int getN() { return n; }
        public void setN(int n) { this.n = n; }

        public int getFindId() { return findId; }
        public void setFindId(int findId) { this.findId = findId; }

        public List<User> getUsers() { return users; }
        public void setUsers(List<User> users) { this.users = users; }
    }

    public static class UserListDeserializer extends JsonDeserializer<List<User>> {
        @Override
        public List<User> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            JsonToken token = p.currentToken();

            // If the current token is an object, treat it as a single object and wrap it in a list
            if (token == JsonToken.START_OBJECT) {
                User user = p.readValueAs(User.class);
                return Collections.singletonList(user);
            }
            // If the current token is an array, deserialize the list of users normally
            else if (token == JsonToken.START_ARRAY) {
                return p.readValueAs(List.class);
            }

            return null;  // handle unexpected cases
        }
    }
}
