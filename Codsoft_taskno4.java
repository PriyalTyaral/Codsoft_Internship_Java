import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Codsoft_taskno4 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Step 1: Allow the user to choose base and target currencies
        System.out.println("Enter the base currency (e.g., USD): ");
        String baseCurrency = scanner.nextLine().toUpperCase();

        System.out.println("Enter the target currency (e.g., EUR): ");
        String targetCurrency = scanner.nextLine().toUpperCase();

        // Step 2: Fetch real-time exchange rates from a reliable API
        double exchangeRate = getExchangeRate(baseCurrency, targetCurrency);
        if (exchangeRate == -1.0) {
            System.out.println("Failed to fetch exchange rate. Please try again later.");
            return;
        }

        // Step 3: Take input from the user for the amount they want to convert
        System.out.println("Enter the amount to convert from " + baseCurrency + " to " + targetCurrency + ": ");
        double amount = scanner.nextDouble();

        // Step 4: Currency Conversion
        double convertedAmount = amount * exchangeRate;

        // Step 5: Display Result
        System.out.printf("%.2f %s = %.2f %s\n", amount, baseCurrency, convertedAmount, targetCurrency);

        scanner.close();
    }

    private static double getExchangeRate(String baseCurrency, String targetCurrency) {
        try {
            URL url = new URL("https://api.exchangerate-api.com/v4/latest/" + baseCurrency);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            if (conn.getResponseCode() == 200) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                reader.close();

                // Parse JSON response to get the exchange rate
                String jsonResponse = response.toString();
                int startIndex = jsonResponse.indexOf("\"" + targetCurrency + "\":");
                int endIndex = jsonResponse.indexOf(",", startIndex);
                String rateString = jsonResponse.substring(startIndex + targetCurrency.length() + 4, endIndex);
                return Double.parseDouble(rateString);
            } else {
                System.out.println("Failed to fetch exchange rate: " + conn.getResponseMessage());
                return -1.0;
            }
        } catch (IOException e) {
            System.out.println("Failed to fetch exchange rate: " + e.getMessage());
            return -1.0;
        }
    }
}