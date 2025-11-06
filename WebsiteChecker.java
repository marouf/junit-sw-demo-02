import java.net.HttpURLConnection;
import java.net.URL;

public class WebsiteChecker {
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
