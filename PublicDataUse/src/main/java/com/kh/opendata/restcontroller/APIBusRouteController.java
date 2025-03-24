package com.kh.opendata.restcontroller;

import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/busroute")
public class APIBusRouteController {

    private static final String serviceKey = "JHF3ucgXiGwhU2VB5J6CVs3a34MpQ1YFrnuQrm%2FMzh16zr0%2F%2FsjvRom6oegE8R2fS70V1Xq0J6Epd";

    @GetMapping
    public String getBusRoute() {
        try {

            StringBuilder apiUrl = new StringBuilder("http://ws.bus.go.kr/api/rest/busRouteInfo/getBusRouteList");
            apiUrl.append("?serviceKey=").append("serviceKey");
            apiUrl.append("&strSrch=3");
            apiUrl.append("&resultType=xml");

            URL requestUrl = new URL(apiUrl.toString());
            HttpURLConnection urlConnection = (HttpURLConnection) requestUrl.openConnection();
            urlConnection.setRequestMethod("GET");

            urlConnection.setRequestProperty("User-Agent", "Mozilla/5.0");
            urlConnection.setRequestProperty("Referer", "https://www.data.go.kr/");
            urlConnection.setRequestProperty("Accept", "application/json");

            BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), StandardCharsets.UTF_8));
            StringBuilder responseText = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                responseText.append(line);
            }

            br.close();
            urlConnection.disconnect();

            return responseText.toString();
        } catch (Exception e) {
            return "{\"error\": \"Failed to fetch data from API.\"}";
        }
    }
}
