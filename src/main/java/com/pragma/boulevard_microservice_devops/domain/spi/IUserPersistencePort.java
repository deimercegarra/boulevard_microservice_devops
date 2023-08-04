package com.pragma.boulevard_microservice_devops.domain.spi;


import com.pragma.boulevard_microservice_devops.domain.model.CommonResponseModel;
import com.pragma.boulevard_microservice_devops.domain.model.UserModel;

public interface IUserPersistencePort {

    public CommonResponseModel findRole(Long userId);

    public CommonResponseModel<UserModel> findUserById(Long userId);

}