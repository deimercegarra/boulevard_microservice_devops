package com.pragma.boulevard_microservice_devops.infrastructure.out.jpa.client.usermicroservice;

import com.pragma.boulevard_microservice_devops.application.dto.request.SmsRequestDto;
import com.pragma.boulevard_microservice_devops.application.dto.request.ValidateCodeRequestDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

//@FeignClient(name = "${messaging_microservice.name}", url = "${messaging_microservice.url}")
//@Repository
public interface TwilioClient {

    @GetMapping("/api/v1/twilio/sms")
    public Map<String, String> notifyOrderStatus(@RequestBody SmsRequestDto smsRequestDto);

    @GetMapping("/api/v1/twilio/send-code")
    public Map<String, String> sendCodeSms(@RequestBody SmsRequestDto smsRequestDto);

    @GetMapping("/api/v1/twilio/valid-code")
    public Map<String, String> validateCodeSms(@RequestBody ValidateCodeRequestDto validateCodeRequestDto);

}
