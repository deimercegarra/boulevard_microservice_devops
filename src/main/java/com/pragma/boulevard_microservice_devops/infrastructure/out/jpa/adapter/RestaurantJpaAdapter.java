package com.pragma.boulevard_microservice_devops.infrastructure.out.jpa.adapter;

import com.pragma.boulevard_microservice_devops.domain.model.RestaurantModel;
import com.pragma.boulevard_microservice_devops.domain.spi.IRestaurantPersistencePort;
import com.pragma.boulevard_microservice_devops.infrastructure.exception.NoDataFoundException;
import com.pragma.boulevard_microservice_devops.infrastructure.exception.RestaurantNotFoundException;
import com.pragma.boulevard_microservice_devops.infrastructure.out.jpa.entity.RestaurantEntity;
import com.pragma.boulevard_microservice_devops.infrastructure.out.jpa.mapper.IRestaurantEntityMapper;
import com.pragma.boulevard_microservice_devops.infrastructure.out.jpa.repository.IRestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RequiredArgsConstructor
public class RestaurantJpaAdapter implements IRestaurantPersistencePort {

    private final IRestaurantRepository iRestaurantRepository;
    private final IRestaurantEntityMapper iRestaurantEntityMapper;

    @Override
    public RestaurantModel saveRestaurant(RestaurantModel restaurantModel) {
        return iRestaurantEntityMapper.toModel(iRestaurantRepository.save(iRestaurantEntityMapper.toEntity(restaurantModel)));
    }

    @Override
    public List<RestaurantModel> getAllRestaurants(Pageable pageable) {
        List<RestaurantEntity> entityList = iRestaurantRepository.findAll(pageable).getContent();
        if (entityList.isEmpty()) {
            throw new NoDataFoundException();
        }
        return iRestaurantEntityMapper.toModelList(entityList);
    }

    @Override
    public RestaurantModel getRestaurant(Long id) {
        return iRestaurantEntityMapper.toModel(iRestaurantRepository.findById(id)
                .orElseThrow(RestaurantNotFoundException::new));
    }

    @Override
    public RestaurantModel updateRestaurant(RestaurantModel restaurantModel) {
        return iRestaurantEntityMapper.toModel(iRestaurantRepository.save(iRestaurantEntityMapper.toEntity(restaurantModel)));
    }

    @Override
    public void deleteRestaurant(Long id) {
        iRestaurantRepository.deleteById(id);
    }

}