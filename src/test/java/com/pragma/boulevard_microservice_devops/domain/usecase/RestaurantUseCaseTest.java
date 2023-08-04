package com.pragma.boulevard_microservice_devops.domain.usecase;

import com.pragma.boulevard_microservice_devops.domain.api.IUserServicePort;
import com.pragma.boulevard_microservice_devops.domain.exception.DomainException;
import com.pragma.boulevard_microservice_devops.domain.model.CommonResponseModel;
import com.pragma.boulevard_microservice_devops.domain.model.RestaurantModel;
import com.pragma.boulevard_microservice_devops.domain.model.UserModel;
import com.pragma.boulevard_microservice_devops.domain.spi.IRestaurantPersistencePort;
import com.pragma.boulevard_microservice_devops.infrastructure.exception.NoDataFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RestaurantUseCaseTest {

    @Mock
    private IRestaurantPersistencePort iRestaurantPersistencePort;

    @Mock
    private IUserServicePort iUserServicePort;

    @InjectMocks
    private RestaurantUseCase restaurantUseCase;

    private UserModel userModel;
    private RestaurantModel restaurantModel;

    @BeforeEach
    void setUp(){
        restaurantModel = new RestaurantModel();

        restaurantModel.setName("lanota");
        restaurantModel.setDirection("turbay ayala");
        restaurantModel.setIdOwner(1L);
        restaurantModel.setPhone("31232");
        restaurantModel.setUrlLogo("fdhgfghfgh/xfnbfgh");
        restaurantModel.setNit("4545453243");
    }

    @Test
    void saveRestaurantTest() {
        CommonResponseModel commonResponseModel = new CommonResponseModel("201", "CREATED.", true);
        when(iUserServicePort.findRole(1L)).thenReturn(commonResponseModel);
        //when(iRestaurantPersistencePort.saveRestaurant(restaurantModel)).thenReturn(restaurantModel);
        assertNotNull(restaurantUseCase.saveRestaurant(restaurantModel));
    }

    @Test
    void saveRestaurantNotSuccessfulTest() {
        CommonResponseModel commonResponseModel = new CommonResponseModel("404", "User not found.", false);
        when(iUserServicePort.findRole(1L)).thenReturn(commonResponseModel);
        //when(iRestaurantPersistencePort.saveRestaurant(any(RestaurantModel.class))).thenReturn(restaurantModel);
        assertNotNull(restaurantUseCase.saveRestaurant(restaurantModel));
    }

    @Test
    void saveRestaurantInternalErrorDomainExceptionTest() {
        doThrow(DomainException.class).when(iUserServicePort).findRole(anyLong());
        assertThrows(DomainException.class, () -> restaurantUseCase.saveRestaurant(restaurantModel));
    }

    @Test
    void getAllRestaurantsNotFoundTest() {

        int page = 1000;
        int size = 10;
        Pageable pageable = PageRequest.of( page-1, size, Sort.by(Sort.Direction.ASC, "name") );

        /*doThrow(NoDataFoundException.class).when(iRestaurantPersistencePort).getAllRestaurants(pageable);
        assertThrows(NoDataFoundException.class, ()-> iRestaurantServicePort.getAllRestaurants(pageable));*/

        when(iRestaurantPersistencePort.getAllRestaurants(pageable)).thenThrow(NoDataFoundException.class);
        assertThrows(NoDataFoundException.class, () -> restaurantUseCase.getAllRestaurants(pageable));
    }

    @Test
    void getAllRestaurantsSuccessfulTest() {

        int page = 1;
        int size = 10;

        Pageable pageable = PageRequest.of( page-1, size, Sort.by(Sort.Direction.ASC, "name") );

        restaurantModel = new RestaurantModel();

        restaurantModel.setName("lanota");
        restaurantModel.setDirection("turbay ayala");
        restaurantModel.setIdOwner(1L);
        restaurantModel.setPhone("31232");
        restaurantModel.setUrlLogo("fdhgfghfgh/xfnbfgh");
        restaurantModel.setNit("4545453243");

        List<RestaurantModel> restaurantList = new ArrayList<>();
        restaurantList.add(restaurantModel);

        when(iRestaurantPersistencePort.getAllRestaurants(pageable)).thenReturn(anyList());
        assertNotNull(iRestaurantPersistencePort.getAllRestaurants(pageable));
        //assertFalse(iRestaurantServicePort.getAllRestaurants(pageable).isEmpty());
    }

}