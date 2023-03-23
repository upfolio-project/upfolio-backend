package com.up.upfolio.services.auth.otp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;


@Component
@Primary
@ConditionalOnProperty(value = "otp.smsprosto.enabled", havingValue = "true")
@Slf4j
public class OtpCodeTransmitterSmsProstoImpl implements OtpCodeTransmitter {
    private final String apiKey;
    private final String host;
    private final String sender;
    private final ObjectMapper objectMapper;

    private final RestTemplate restTemplate;

    private static final String MESSAGE_TEMPLATE = "Ваш одноразовый код для UpFolio: %s";

    public OtpCodeTransmitterSmsProstoImpl(@Value("${otp.smsprosto.key}") String apiKey,
                                           @Value("${otp.smsprosto.host:https://ssl.bs00.ru}") String host,
                                           @Value("${otp.smsprosto.sender:UpFolio}") String sender, ObjectMapper mapper) {
        this.apiKey = apiKey;
        this.restTemplate = new RestTemplate();
        this.host = host;
        this.sender = sender;
        this.objectMapper = mapper;

        log.info("sms-prosto is loaded and would be used for OTP");
    }

    @Override
    public void sendCode(String phoneNumber, String code) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("key", apiKey);
        params.add("method", "push_msg");
        params.add("format", "json");
        params.add("phone", phoneNumber);
        params.add("sender_name", sender);
        params.add("text", interpolate(code));

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params, headers);

        ResponseEntity<String> result = restTemplate.postForEntity(host, entity, String.class);
        checkSuccess(result);
    }

    private String interpolate(String code) {
        return MESSAGE_TEMPLATE.formatted(code);
    }

    // due to bad-designed sms-prosto API we have to check success manually
    private void checkSuccess(ResponseEntity<String> result) {
        if (!result.getStatusCode().is2xxSuccessful()) {
            log.error("OTP code transmitter reported non-200 status code: {}\nfull response object: {}", result.getStatusCode(), result.getBody());
            return;
        }

        final String errorLogTemplate = "unable to check OTP code transmitter response value\nfull response object: {}";

        try {
            String body = result.getBody();
            
            JsonNode tree = objectMapper.readTree(body);

            JsonNode responseWrapped = tree.get("response");
            if (responseWrapped == null) {
                log.warn(errorLogTemplate, body);
                return;
            }

            JsonNode msg = responseWrapped.get("msg");
            if (msg == null) {
                log.warn(errorLogTemplate, body);
                return;
            }

            JsonNode errCode = msg.get("err_code");
            if (errCode == null) {
                log.warn(errorLogTemplate, body);
                return;
            }

            if (!"0".equals(errCode.asText("1"))) {
                log.warn("OTP code transmitter reported non-zero status code\nfull response object: {}", body);
            }

        } catch (JsonProcessingException e) {
            log.error("json processing exception", e);
        }
    }
}
