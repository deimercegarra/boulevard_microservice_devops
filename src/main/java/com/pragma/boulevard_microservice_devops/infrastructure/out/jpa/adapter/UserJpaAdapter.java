package com.pragma.boulevard_microservice_devops.infrastructure.out.jpa.adapter;

import com.pragma.boulevard_microservice_devops.application.mapper.ICommonResponseMapper;
import com.pragma.boulevard_microservice_devops.domain.model.CommonResponseModel;
import com.pragma.boulevard_microservice_devops.domain.model.UserModel;
import com.pragma.boulevard_microservice_devops.domain.spi.IUserPersistencePort;
import com.pragma.boulevard_microservice_devops.infrastructure.out.jpa.client.usermicroservice.UserClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@RequiredArgsConstructor
public class UserJpaAdapter implements IUserPersistencePort {

    //@Autowired
    private UserClient userClient;
    private final ICommonResponseMapper iCommonResponseMapper;

    @Override
    public CommonResponseModel findRole(Long userId) {
        return iCommonResponseMapper.toModel(userClient.findRole(userId));
    }

    @Override
    public CommonResponseModel<UserModel> findUserById(Long userId) {
        return iCommonResponseMapper.toModel(userClient.findUser(userId));
    }

}