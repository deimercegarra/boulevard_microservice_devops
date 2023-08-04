package com.pragma.boulevard_microservice_devops.infrastructure.out.jpa.client.usermicroservice;

import com.pragma.boulevard_microservice_devops.application.dto.response.CommonResponseDto;
import com.pragma.boulevard_microservice_devops.domain.model.UserModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name = "${user_microservice.name}", url = "${user_microservice.url}")
//@Repository
public interface UserClient {

    @GetMapping("/api/v1/user/findRole/{userId}")
    public CommonResponseDto findRole(@PathVariable Long userId);

    @GetMapping("/api/v1/user/findUser/{userId}")
    public CommonResponseDto<UserModel> findUser(@PathVariable Long userId);

}
