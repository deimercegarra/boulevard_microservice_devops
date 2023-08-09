package com.pragma.boulevard_microservice_devops.infrastructure.configuration;

import com.pragma.boulevard_microservice_devops.application.mapper.ICommonResponseMapper;
import com.pragma.boulevard_microservice_devops.domain.api.IDishServicePort;
import com.pragma.boulevard_microservice_devops.domain.api.IOrderServicePort;
import com.pragma.boulevard_microservice_devops.domain.api.IRestaurantServicePort;
import com.pragma.boulevard_microservice_devops.domain.api.IUserServicePort;
import com.pragma.boulevard_microservice_devops.domain.spi.*;
import com.pragma.boulevard_microservice_devops.domain.usecase.DishUseCase;
import com.pragma.boulevard_microservice_devops.domain.usecase.OrderUseCase;
import com.pragma.boulevard_microservice_devops.domain.usecase.RestaurantUseCase;
import com.pragma.boulevard_microservice_devops.domain.usecase.UserUseCase;
import com.pragma.boulevard_microservice_devops.infrastructure.out.jpa.adapter.*;
import com.pragma.boulevard_microservice_devops.infrastructure.out.jpa.mapper.*;
import com.pragma.boulevard_microservice_devops.infrastructure.out.jpa.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final ICategoryRepository iCategoryRepository;
    private final ICategoryEntityMapper iCategoryEntityMapper;

    private final IDishRepository iDishRepository;
    private final IDishEntityMapper iDishEntityMapper;

    private final IOrderRepository iOrderRepository;
    private final IOrderEntityMapper iOrderEntityMapper;

    private final IOrderDishRepository iOrderDishRepository;
    private final IOrderDishEntityMapper iOrderDishEntityMapper;

    private final IRestaurantRepository iRestaurantRepository;
    private final IRestaurantEntityMapper iRestaurantEntityMapper;

    //private final UserClient userClient;
    private final ICommonResponseMapper iCommonResponseMapper;
    private final IEmployeeRepository iEmployeeRepository;
    private final IEmployeeEntityMapper iEmployeeEntityMapper;
    //private final TwilioClient twilioClient;

    @Bean
    public ICategoryPersistencePort iCategoryPersistencePort() {
        return new CategoryJpaAdapter(iCategoryRepository, iCategoryEntityMapper);
    }


    @Bean
    public IDishPersistencePort iDishPersistencePort() {
        return new DishJpaAdapter(iDishRepository, iDishEntityMapper, iCategoryRepository, iRestaurantRepository);
    }
    @Bean
    public IDishServicePort dishServicePort() {
        return new DishUseCase(iDishPersistencePort(), iRestaurantPersistencePort());
    }

    @Bean
    public IOrderPersistencePort iOrderPersistencePort() {
        return new OrderJpaAdapter(iOrderRepository, iOrderEntityMapper);
    }
    @Bean
    public IOrderServicePort orderServicePort() {
        return new OrderUseCase(iOrderPersistencePort(), iOrderDishPersistencePort(), iEmployeePersistencePort(), iUserPersistencePort(), iMessagingPersistencePort());
    }

    @Bean
    public IMessagingPersistencePort iMessagingPersistencePort(){
        return new MessagingJpaAdapter();
    }

    @Bean
    public IEmployeePersistencePort iEmployeePersistencePort(){
        return new EmployeeJpaAdapter(iEmployeeRepository, iEmployeeEntityMapper);
    }

    @Bean
    public IOrderDishPersistencePort iOrderDishPersistencePort() {
        return new OrderDishJpaAdapter(iOrderDishRepository, iDishRepository, iOrderDishEntityMapper);
    }

    @Bean
    public IRestaurantPersistencePort iRestaurantPersistencePort() {
        return new RestaurantJpaAdapter(iRestaurantRepository, iRestaurantEntityMapper);
    }

    @Bean
    public IUserServicePort iUserServicePort() {
        return new UserUseCase(iUserPersistencePort());
    }

    @Bean
    public IUserPersistencePort iUserPersistencePort() {
        return new UserJpaAdapter(iCommonResponseMapper);
    }
    @Bean
    public IRestaurantServicePort restaurantServicePort() {
        return new RestaurantUseCase(iRestaurantPersistencePort(), iUserServicePort());
    }

}