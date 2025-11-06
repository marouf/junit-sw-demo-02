import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class EmployeeSalaryCalculator {
    public double calculateAverageSalary(int[] salaries) {
        if (salaries == null || salaries.length == 0) {
            throw new IllegalArgumentException("Salaries array cannot be null or empty");
        }
        double sum = 0;
        for (int salary : salaries) {
            sum += salary;
        }
        return sum / salaries.length;
    }
}

class CourseProgressCalculator {
    public double calculateProgressPercentage(int completedLessons, int totalLessons) {
        if (totalLessons <= 0) {
            throw new IllegalArgumentException("Total lessons must be greater than zero");
        }
        if (completedLessons < 0 || completedLessons > totalLessons) {
            throw new IllegalArgumentException("Completed lessons must be between 0 and total lessons");
        }
        return ((double) completedLessons / totalLessons) * 100;
    }
}

class WebsiteChecker {
    public boolean isWebsiteOnline(String urlString) {
        try {
            long startTime = System.currentTimeMillis();
            HttpURLConnection connection = (HttpURLConnection) new URL(urlString).openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.connect();
            long endTime = System.currentTimeMillis();

            int code = connection.getResponseCode();
            System.out.println("Response Code: " + code);
            System.out.println("Access Time: " + (endTime - startTime) + " ms");

            return code == 200;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }
}

class ApiClient {

    public String sendGet(String urlString) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(urlString).openConnection();
            connection.setRequestMethod("GET");

            Scanner scanner = new Scanner(connection.getInputStream());
            StringBuilder response = new StringBuilder();
            while (scanner.hasNext()) {
                response.append(scanner.nextLine());
            }
            scanner.close();
            return response.toString();
        } catch (Exception e) {
            return "GET Error: " + e.getMessage();
        }
    }

    public String sendPost(String urlString, String jsonPayload) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(urlString).openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            OutputStream os = connection.getOutputStream();
            os.write(jsonPayload.getBytes());
            os.flush();
            os.close();

            Scanner scanner = new Scanner(connection.getInputStream());
            StringBuilder response = new StringBuilder();
            while (scanner.hasNext()) {
                response.append(scanner.nextLine());
            }
            scanner.close();
            return response.toString();
        } catch (Exception e) {
            return "POST Error: " + e.getMessage();
        }
    }

    public String sendDelete(String urlString) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(urlString).openConnection();
            connection.setRequestMethod("DELETE");

            int responseCode = connection.getResponseCode();
            return "DELETE Response Code: " + responseCode;
        } catch (Exception e) {
            return "DELETE Error: " + e.getMessage();
        }
    }
}




import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalculatorsTest {

    private final EmployeeSalaryCalculator salaryCalculator = new EmployeeSalaryCalculator();
    private final CourseProgressCalculator progressCalculator = new CourseProgressCalculator();
    private final WebsiteChecker checker = new WebsiteChecker();
    private final ApiClient apiClient = new ApiClient();

    // EmployeeSalaryCalculator Tests
    @Test
    void testCalculateAverageSalary() {
        int[] salaries = {5000, 6000, 7000};
        assertEquals(6000.0, salaryCalculator.calculateAverageSalary(salaries));
    }

    @Test
    void testCalculateAverageSalaryWithEmptyArray() {
        int[] salaries = {};
        assertThrows(IllegalArgumentException.class, () -> salaryCalculator.calculateAverageSalary(salaries));
    }

    // CourseProgressCalculator Tests
    @Test
    void testCalculateProgressPercentage() {
        assertEquals(50.0, progressCalculator.calculateProgressPercentage(5, 10));
    }

    @Test
    void testCalculateProgressPercentageWithZeroTotalLessons() {
        assertThrows(IllegalArgumentException.class, () -> progressCalculator.calculateProgressPercentage(5, 0));
    }

    // WebsiteChecker Test
    @Test
    void testWebsiteIsOnline() {
        assertTrue(checker.isWebsiteOnline("https://www.aaup.edu"));
    }

    // API Client Tests (example URLs, replace with real endpoints)
    @Test
    void testSendGet() {
        String response = apiClient.sendGet("https://jsonplaceholder.typicode.com/posts/1");
        assertTrue(response.contains("userId"));
    }

    @Test
    void testSendPost() {
        String json = "{\"title\":\"foo\",\"body\":\"bar\",\"userId\":1}";
        String response = apiClient.sendPost("https://jsonplaceholder.typicode.com/posts", json);
        assertTrue(response.contains("id"));
    }

    @Test
    void testSendDelete() {
        String response = apiClient.sendDelete("https://jsonplaceholder.typicode.com/posts/1");
        assertTrue(response.contains("DELETE Response Code"));
    }
}