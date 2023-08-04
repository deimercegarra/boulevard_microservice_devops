package com.pragma.boulevard_microservice_devops.domain.usecase;

import com.pragma.boulevard_microservice_devops.domain.api.IDishServicePort;
import com.pragma.boulevard_microservice_devops.domain.exception.DomainException;
import com.pragma.boulevard_microservice_devops.domain.model.CategoryModel;
import com.pragma.boulevard_microservice_devops.domain.model.DishModel;
import com.pragma.boulevard_microservice_devops.domain.model.RestaurantModel;
import com.pragma.boulevard_microservice_devops.domain.spi.IDishPersistencePort;
import com.pragma.boulevard_microservice_devops.domain.spi.IRestaurantPersistencePort;
import com.pragma.boulevard_microservice_devops.domain.spi.IUserPersistencePort;
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
class DishUseCaseTest {

    @Mock
    private IDishPersistencePort iDishPersistencePort;

    @Mock
    private IDishServicePort iDishServicePort;

    @InjectMocks
    private DishUseCase dishUseCase;

    @Mock
    private IRestaurantPersistencePort iRestaurantPersistencePort;

    @Mock
    private IUserPersistencePort iUserPersistencePort;

    private DishModel dishModel;
    private CategoryModel categoryModel;

    private RestaurantModel restaurantModel;

    @BeforeEach
    void setUp(){

        /*iDishPersistencePort = mock(IDishPersistencePort.class);
        iRestaurantPersistencePort = mock(IRestaurantPersistencePort.class);
        iUserPersistencePort = mock(IUserPersistencePort.class);
        iDishServicePort = mock(IDishServicePort.class);
        dishUseCase = new DishUseCase(iDishPersistencePort, iRestaurantPersistencePort, iUserPersistencePort);
        MockitoAnnotations.initMocks(this);*/

        dishModel = new DishModel();

        dishModel.setName("ceviche");
        dishModel.setCategoryId(1L);
        dishModel.setDescription("prueba");
        dishModel.setPrice(123564);
        dishModel.setRestaurantId(11L);
        dishModel.setUrlImage("dfgf/dfgfh");
        dishModel.setActive(true);
        dishModel.setUserId(2L);
    }

    @Test
    void saveDish() {
        restaurantModel = new RestaurantModel();

        restaurantModel.setId(11L);
        restaurantModel.setName("lanota");
        restaurantModel.setDirection("turbay ayala");
        restaurantModel.setIdOwner(2L);
        restaurantModel.setPhone("31232");
        restaurantModel.setUrlLogo("fdhgfghfgh/xfnbfgh");
        restaurantModel.setNit("4545453243");

        when(iRestaurantPersistencePort.getRestaurant(restaurantModel.getId())).thenReturn(restaurantModel);
        when(iDishPersistencePort.saveDish(dishModel)).thenReturn(dishModel);
        assertNotNull(dishUseCase.saveDish(dishModel));
    }

    @Test
    void saveDishRestaurantNotFoundDomainExceptionTest() {
        doThrow(DomainException.class).when(iRestaurantPersistencePort).getRestaurant(anyLong());
        assertThrows(DomainException.class, () -> dishUseCase.saveDish(dishModel));
    }

    @Test
    void saveDishUnauthorizedUserDomainExceptionTest() {
        dishModel = new DishModel();

        dishModel.setName("ceviche");
        dishModel.setCategoryId(1L);
        dishModel.setDescription("prueba");
        dishModel.setPrice(123564);
        dishModel.setRestaurantId(11L);
        dishModel.setUrlImage("dfgf/dfgfh");
        dishModel.setActive(true);
        dishModel.setUserId(1L);

        doThrow(DomainException.class).when(iRestaurantPersistencePort).getRestaurant(dishModel.getRestaurantId());
        assertThrows(DomainException.class, () -> dishUseCase.saveDish(dishModel));
    }

    @Test
    void updateDishTest() {
        dishModel = new DishModel();

        dishModel.setId(2L);
        dishModel.setName("ceviche");
        dishModel.setCategoryId(1L);
        dishModel.setDescription("prueba");
        dishModel.setPrice(123564);
        dishModel.setRestaurantId(11L);
        dishModel.setUrlImage("dfgf/dfgfh");
        dishModel.setActive(true);
        dishModel.setUserId(2L);

        when(iDishPersistencePort.getDish(dishModel.getId())).thenReturn(dishModel);
        when(iDishPersistencePort.updateDish(dishModel)).thenReturn(dishModel);
        assertNotNull(dishUseCase.updateDish(dishModel));
    }

    @Test
    void updateDishNotFoundDomainExceptionTest() {
        dishModel = new DishModel();

        dishModel.setId(0L);
        dishModel.setName("ceviche");
        dishModel.setCategoryId(1L);
        dishModel.setDescription("prueba");
        dishModel.setPrice(123564);
        dishModel.setRestaurantId(11L);
        dishModel.setUrlImage("dfgf/dfgfh");
        dishModel.setActive(true);
        dishModel.setUserId(4L);

        doThrow(DomainException.class).when(iDishPersistencePort).getDish(dishModel.getId());
        assertThrows(DomainException.class, () -> dishUseCase.updateDish(dishModel));
    }

    @Test
    void updateActiveDishTest() {
        dishModel = new DishModel();

        dishModel.setId(1L);
        dishModel.setActive(false);
        dishModel.setUserId(1L);

        when(iDishPersistencePort.getDish(dishModel.getId())).thenReturn(dishModel);
        when(iDishPersistencePort.activeDish(dishModel)).thenReturn(dishModel);
        assertNotNull(dishUseCase.activeDish(dishModel));
    }

    @Test
    void activeDishNotFoundDomainExceptionTest() {
        dishModel = new DishModel();

        dishModel.setId(1L);
        dishModel.setActive(true);
        dishModel.setUserId(1L);

        when(iDishPersistencePort.getDish(dishModel.getId())).thenThrow(DomainException.class);
        assertThrows(DomainException.class, () -> dishUseCase.activeDish(dishModel));
    }

    @Test
    void getAllDishesNotFoundTest() {

        int page = 1000;
        int size = 10;
        Pageable pageable = PageRequest.of( page-1, size, Sort.by(Sort.Direction.ASC, "name") );

        when(iDishPersistencePort.findDishesByRestaurantAndCategory(1L, 1L, pageable)).thenThrow(NoDataFoundException.class);
        assertThrows(NoDataFoundException.class, () -> dishUseCase.getDishesByRestaurantAndCategory(1L, 1L, pageable));
    }

    @Test
    void getAllDishesSuccessfulTest() {

        int page = 1;
        int size = 10;

        Pageable pageable = PageRequest.of( page-1, size, Sort.by(Sort.Direction.ASC, "name") );

        Long idRestaurant = 1L;
        Long idCategory = 1L;

        dishModel = new DishModel();

        dishModel.setId(0L);
        dishModel.setName("ceviche");
        dishModel.setCategoryId(1L);
        dishModel.setDescription("prueba");
        dishModel.setPrice(123564);
        dishModel.setRestaurantId(11L);
        dishModel.setUrlImage("dfgf/dfgfh");
        dishModel.setActive(true);
        dishModel.setUserId(4L);

        List<DishModel> dishModelList =  new ArrayList<>();
        dishModelList.add(dishModel);

        //when(iDishPersistencePort.findDishesByRestaurantAndCategory(idRestaurant, idCategory, pageable)).thenReturn(dishModelList);
        assertNotNull(iDishServicePort.getDishesByRestaurantAndCategory(idRestaurant, idCategory, pageable));
    }

}