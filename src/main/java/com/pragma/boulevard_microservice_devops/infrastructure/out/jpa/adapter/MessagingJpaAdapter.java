package com.pragma.boulevard_microservice_devops.infrastructure.out.jpa.adapter;

import com.pragma.boulevard_microservice_devops.application.dto.request.SmsRequestDto;
import com.pragma.boulevard_microservice_devops.application.dto.request.ValidateCodeRequestDto;
import com.pragma.boulevard_microservice_devops.domain.spi.IMessagingPersistencePort;
import com.pragma.boulevard_microservice_devops.infrastructure.exception.SendMessageException;
import com.pragma.boulevard_microservice_devops.infrastructure.out.jpa.client.usermicroservice.TwilioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

@RequiredArgsConstructor
public class MessagingJpaAdapter implements IMessagingPersistencePort {

    //@Autowired
    private TwilioClient twilioClient;

    @Override
    public void notifyOrderStatus(String message, String phone) {
        SmsRequestDto smsRequestDto;
        smsRequestDto = new SmsRequestDto(message, phone);
        twilioClient.notifyOrderStatus(smsRequestDto);
    }

    @Override
    public Map<String, String> sendCodeVerification(String message, String phone) {
        SmsRequestDto smsRequestDto;
        smsRequestDto = new SmsRequestDto(message, phone);
        Map<String, String> responseMap = null;

        try {
            responseMap = twilioClient.sendCodeSms(smsRequestDto);
        } catch (Exception e) {
            throw new SendMessageException("Verify the customer's phone number.");
        }
        return responseMap;
    }

    @Override
    public Map<String, String> validateCodeVerification(String code, String phone) {
        ValidateCodeRequestDto validateCodeRequestDto;
        validateCodeRequestDto = new ValidateCodeRequestDto(code, phone);
        Map<String, String> responseMap = null;

        try {
            responseMap = twilioClient.validateCodeSms(validateCodeRequestDto);
        } catch (Exception e) {
            throw new SendMessageException();
        }
        return responseMap;
    }

}
