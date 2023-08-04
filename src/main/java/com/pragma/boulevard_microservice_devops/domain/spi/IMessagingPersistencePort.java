package com.pragma.boulevard_microservice_devops.domain.spi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

public interface IMessagingPersistencePort {

    void notifyOrderStatus(String message, String phone);

    Map<String, String> sendCodeVerification(String message, String phone);

    Map<String, String> validateCodeVerification(String code, String phone);

}