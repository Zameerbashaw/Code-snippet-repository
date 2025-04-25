package bajaj.com.finserv.service;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import bajaj.com.finserv.model.User;
import bajaj.com.finserv.model.WebhookResponse;

import java.util.*;

@Service
public class WebhookService {

    private final RestTemplate restTemplate = new RestTemplate();

    public void execute() throws InterruptedException {
        String regNo = "REG12347";
        String name = "John Doe";
        String email = "john@example.com";

        String generateUrl = "https://bfhldevapigw.healthrx.co.in/hiring/generateWebhook";

        // Step 1: Send initial request
        Map<String, String> requestMap = Map.of("name", name, "regNo", regNo, "email", email);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, String>> entity = new HttpEntity<>(requestMap, headers);

        ResponseEntity<WebhookResponse> response = restTemplate.exchange(
                generateUrl,
                HttpMethod.POST,
                entity,
                new ParameterizedTypeReference<>() {}
        );

        WebhookResponse body = response.getBody();
        if (body == null) return;

        List<User> users = body.getData().getUsers();
        String webhook = body.getWebhook();
        String token = body.getAccessToken();

        Map<String, Object> output = new HashMap<>();
        output.put("regNo", regNo);

        // Step 2: Decide question
        String digits = regNo.replaceAll("\\D", "");
        if (digits.length() < 2) {
            throw new IllegalArgumentException("Registration number does not contain enough digits");
        }
        int lastTwoDigits = Integer.parseInt(digits.substring(digits.length() - 2));
        boolean isOdd = lastTwoDigits % 2 != 0;

        if (isOdd) {
            output.put("outcome", solveMutualFollowers(users));
        } else {
            int n = body.getData().getN();
            int findId = body.getData().getFindId();
            output.put("outcome", solveNthLevel(users, findId, n));
        }

        // Step 3: Send result (retry up to 4 times)
        HttpHeaders postHeaders = new HttpHeaders();
        postHeaders.set("Authorization", token);
        postHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, Object>> postReq = new HttpEntity<>(output, postHeaders);

        int attempt = 0;
        boolean success = false;

        while (attempt < 4 && !success) {
            try {
                restTemplate.postForEntity(webhook, postReq, String.class);
                success = true;
            } catch (Exception e) {
                attempt++;
                Thread.sleep(1000);
            }
        }
    }

    private List<List<Integer>> solveMutualFollowers(List<User> users) {
        Map<Integer, Set<Integer>> followsMap = new HashMap<>();
        for (User user : users) {
            followsMap.put(user.getId(), new HashSet<>(user.getFollows()));
        }

        Set<String> seen = new HashSet<>();
        List<List<Integer>> mutuals = new ArrayList<>();

        for (User u : users) {
            for (Integer f : u.getFollows()) {
                if (followsMap.containsKey(f) && followsMap.get(f).contains(u.getId())) {
                    int a = Math.min(u.getId(), f);
                    int b = Math.max(u.getId(), f);
                    String key = a + "-" + b;
                    if (!seen.contains(key)) {
                        mutuals.add(List.of(a, b));
                        seen.add(key);
                    }
                }
            }
        }
        return mutuals;
    }

    private List<Integer> solveNthLevel(List<User> users, int findId, int n) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (User user : users) {
            graph.put(user.getId(), user.getFollows());
        }

        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(findId);
        visited.add(findId);

        int level = 0;
        while (!queue.isEmpty() && level < n) {
            int size = queue.size();
            while (size-- > 0) {
                int curr = queue.poll();
                for (int next : graph.getOrDefault(curr, List.of())) {
                    if (visited.add(next)) {
                        queue.add(next);
                    }
                }
            }
            level++;
        }

        return new ArrayList<>(queue);
    }
}
