package com.kh.opendata.restcontroller;

import org.springframework.web.bind.annotation.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/air.do")
public class APIAirController {

    private static final String serviceKey = "RlrMNFCsyGmkQQqIJ9pI5aDYSEh5TrqYUKrRtPBOysCzwa%2FrmgSlGQbRZp4%2FHpyw0k4Z2QACgWDBUzeCJqUJZw%3D%3D";

    @GetMapping
    public String airPollution() {
        try {

            String encodedSidoName = URLEncoder.encode("서울", StandardCharsets.UTF_8);

            String apiUrl = "https://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty"
                    + "?serviceKey=" + serviceKey
                    + "&returnType=json"
                    + "&numOfRows=100"
                    + "&pageNo=1"
                    + "&sidoName=" + encodedSidoName
                    + "&ver=1.0";

            URL requestUrl = new URL(apiUrl);
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
