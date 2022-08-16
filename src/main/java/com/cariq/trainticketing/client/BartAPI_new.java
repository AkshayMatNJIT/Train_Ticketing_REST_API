package com.cariq.trainticketing.client;

import com.cariq.trainticketing.exception.InvalidSourceOrDestinationException;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import java.net.URI;

@Slf4j
public class BartAPI_new {

    private final RestTemplate restTemplate;

    @Value("${application.api.key}")
    private String key;

    private static final String BART_API_GET_FARE = "http://api.bart.gov/api/sched.aspx?cmd=fare&orig=%s&dest=%s&date=today&key=%s&json=y";

    public BartAPI_new(RestTemplate template) {
        restTemplate = template;
    }

    public double getFare(String source, String destination) throws InvalidSourceOrDestinationException {
        log.info("Getting fare from Bart API");
        URI uri = URI.create(String.format(BART_API_GET_FARE, source, destination, key));
        ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.GET, null, String.class);
        return parse(response.getBody());
    }

    public double parse(String responseBody) {
        JSONObject json = new JSONObject(responseBody);
        JSONObject root = json.getJSONObject("root");
        JSONObject trip = root.getJSONObject("trip");
        return trip.getDouble("fare");
    }
}