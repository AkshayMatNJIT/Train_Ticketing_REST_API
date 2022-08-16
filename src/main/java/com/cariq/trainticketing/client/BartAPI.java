package com.cariq.trainticketing.client;

import com.cariq.trainticketing.exception.InvalidSourceOrDestinationException;
import com.cariq.trainticketing.exception.UserWalletNotFoundException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class BartAPI {

    private HttpURLConnection conn;
    public double getFare(String source, String destination) throws InvalidSourceOrDestinationException {
        BufferedReader reader;
        String line;
        StringBuilder responseContent = new StringBuilder();
        try {
            URL url = new URL("http://api.bart.gov/api/sched.aspx?cmd=fare&orig=" + source + "&dest=" + destination + "&date=today&key=MW9S-E7SL-26DU-VV8V&json=y");
            conn = (HttpURLConnection) url.openConnection();

            // Request setup
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(3000);   // 3000 milliseconds = 3 seconds
            conn.setReadTimeout(3000);

            // Test if the response from the server is successful
            int status = conn.getResponseCode();

            if (status == 400) {
                throw new InvalidSourceOrDestinationException("Invalid origin or destination entered");
            }
            else if (status >= 300) {
                reader = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }
            else {
                reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            }
            while ((line = reader.readLine()) != null) {
                responseContent.append(line);
            }
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
        return parse(responseContent.toString());
    }

    public double parse(String responseBody) {
        JSONObject json = new JSONObject(responseBody);
        JSONObject root = json.getJSONObject("root");
        JSONObject trip = root.getJSONObject("trip");
        double fare = trip.getDouble("fare");
        return fare;
    }
}