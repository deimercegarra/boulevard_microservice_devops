package com.pragma.boulevard_microservice_devops.domain.usecase;

import com.pragma.boulevard_microservice_devops.domain.api.IDishServicePort;
import com.pragma.boulevard_microservice_devops.domain.exception.DomainException;
import com.pragma.boulevard_microservice_devops.domain.model.CommonResponseModel;
import com.pragma.boulevard_microservice_devops.domain.model.DishModel;
import com.pragma.boulevard_microservice_devops.domain.model.RestaurantModel;
import com.pragma.boulevard_microservice_devops.domain.spi.IDishPersistencePort;
import com.pragma.boulevard_microservice_devops.domain.spi.IRestaurantPersistencePort;
import com.pragma.boulevard_microservice_devops.domain.spi.IUserPersistencePort;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class DishUseCase implements IDishServicePort {

    private final IDishPersistencePort iDishPersistencePort;
    private final IRestaurantPersistencePort iRestaurantPersistencePort;
    public DishUseCase(IDishPersistencePort iDishPersistencePort,
                       IRestaurantPersistencePort iRestaurantPersistencePort) {
        this.iDishPersistencePort = iDishPersistencePort;
        this.iRestaurantPersistencePort = iRestaurantPersistencePort;
    }

    @Override
    public List<DishModel> getDishesByRestaurantAndCategory(Long idRestaurant, Long idCategory, Pageable pageable) {
        return iDishPersistencePort.findDishesByRestaurantAndCategory( idRestaurant, idCategory, pageable );
    }

    @Override
    public CommonResponseModel saveDish(DishModel dishModel) {
        RestaurantModel restaurantModel = iRestaurantPersistencePort.getRestaurant(dishModel.getRestaurantId());

        if (restaurantModel == null)
            throw new DomainException("Restaurant not found.");

        if (! (dishModel.getUserId().equals(restaurantModel.getIdOwner())) )
            throw new DomainException("Unauthorized user.");

        iDishPersistencePort.saveDish(dishModel);

        return new CommonResponseModel("201","CREATED.", true);
    }

    @Override
    public CommonResponseModel updateDish(DishModel dishModel) {

        DishModel dishModelDb = iDishPersistencePort.getDish(dishModel.getId());

        if(dishModelDb == null)
            throw new DomainException("Dish not found.");

        iDishPersistencePort.updateDish(dishModel);

        return new CommonResponseModel("200","Dish Updated.", true);
    }

    @Override
    public CommonResponseModel activeDish(DishModel dishModel) {
        DishModel dishModelDb = iDishPersistencePort.getDish(dishModel.getId());

        if(dishModelDb == null) {
            throw new DomainException();
        }

        iDishPersistencePort.activeDish(dishModel);

        return new CommonResponseModel("200","Dish Updated.", true);
    }
}