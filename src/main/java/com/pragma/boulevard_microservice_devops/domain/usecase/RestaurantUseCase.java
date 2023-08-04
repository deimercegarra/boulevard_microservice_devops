package com.pragma.boulevard_microservice_devops.domain.usecase;

import com.pragma.boulevard_microservice_devops.domain.api.IRestaurantServicePort;
import com.pragma.boulevard_microservice_devops.domain.api.IUserServicePort;
import com.pragma.boulevard_microservice_devops.domain.exception.DomainException;
import com.pragma.boulevard_microservice_devops.domain.model.CommonResponseModel;
import com.pragma.boulevard_microservice_devops.domain.model.RestaurantModel;
import com.pragma.boulevard_microservice_devops.domain.spi.IRestaurantPersistencePort;
import com.pragma.boulevard_microservice_devops.infrastructure.configuration.Constants;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class RestaurantUseCase implements IRestaurantServicePort {

    private final IRestaurantPersistencePort iRestaurantPersistencePort;
    private final IUserServicePort iUserServicePort;

    public RestaurantUseCase(IRestaurantPersistencePort iRestaurantPersistencePort,
                             IUserServicePort iUserServicePort) {
        this.iRestaurantPersistencePort = iRestaurantPersistencePort;
        this.iUserServicePort = iUserServicePort;
    }

    @Override
    public CommonResponseModel saveRestaurant(RestaurantModel restaurantModel) {
        CommonResponseModel commonResponseModel = new CommonResponseModel();

        try {
            commonResponseModel = iUserServicePort.findRole(restaurantModel.getIdOwner());
        } catch (Exception ex) {
            throw new DomainException(new CommonResponseModel("500","Internal error.", false).toString());
        }

        if (!commonResponseModel.getStatus()) {
            return new CommonResponseModel(commonResponseModel.getCode(),commonResponseModel.getMessage(), false);
        }

        if (!commonResponseModel.getMessage().equalsIgnoreCase(Constants.ROLE_ADMIN)) {
            return new CommonResponseModel("202","The role is not Administrator.", false);
        }

        iRestaurantPersistencePort.saveRestaurant(restaurantModel);

        return new CommonResponseModel("201","CREATED.", true);
    }

    @Override
    public List<RestaurantModel> getAllRestaurants(Pageable pageable) {
        return iRestaurantPersistencePort.getAllRestaurants(pageable);
    }

}