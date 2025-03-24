package com.kh.opendata.restcontroller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/busstation.do")
public class APIBusStationController {

    private static final String SERVICE_KEY = "JHFf3ucgXiGwHUzVB5J6CVs3a34MpQ1YFrnuQrm%2FlMzh16zr0%2FfsjvRom6oegE8R2F570V1XqOj6EpGFlh7A%3D%3D";

    @GetMapping
    public ResponseEntity<Map<String, Object>> getBusRoutes() {
        try {
            String encodedRouteId = URLEncoder.encode("100100112", StandardCharsets.UTF_8);

            String apiUrl = "http://ws.bus.go.kr/api/rest/busRouteInfo/getStaionByRoute"
                    + "?serviceKey=" + "SERVICE_KEY"
                    + "&busRouteId=" + encodedRouteId
                    + "&resultType=xml";

            RestTemplate restTemplate = new RestTemplate();
            String response = restTemplate.getForObject(apiUrl, String.class);

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(response);
            Map<String, Object> result = objectMapper.convertValue(jsonNode, Map.class);

            return ResponseEntity.ok(result);
        } catch (IOException e) {
            return ResponseEntity.badRequest().body(Map.of("error", "데이터를 가져올 수 없습니다."));
        }
    }
}
