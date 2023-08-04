package com.pragma.boulevard_microservice_devops.domain.api;


import com.pragma.boulevard_microservice_devops.domain.model.CommonResponseModel;

public interface IUserServicePort {

    public CommonResponseModel findRole(Long userId);

}