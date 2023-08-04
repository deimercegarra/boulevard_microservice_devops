package com.pragma.boulevard_microservice_devops.infrastructure.input.rest;

import com.pragma.boulevard_microservice_devops.application.dto.request.ActiveDishRequestDto;
import com.pragma.boulevard_microservice_devops.application.dto.request.DishRequestDto;
import com.pragma.boulevard_microservice_devops.application.dto.request.DishUpdateRequestDto;
import com.pragma.boulevard_microservice_devops.application.dto.response.CommonResponseDto;
import com.pragma.boulevard_microservice_devops.application.dto.response.DishResponseDto;
import com.pragma.boulevard_microservice_devops.application.handler.IDishHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/dish")
@RequiredArgsConstructor
public class DishRestController {

    private final IDishHandler iDishHandler;

    @Operation(summary = "Add a new Dish")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Dish created.", content = @Content),
            @ApiResponse(responseCode = "202", description = "Request accepted but unsuccessful.", content = @Content),
            @ApiResponse(responseCode = "409", description = "Dish already exists.", content = @Content)
    })
    @PostMapping("/")
    public ResponseEntity<CommonResponseDto> saveDish(@Valid  @RequestBody DishRequestDto DishRequestDto) {
        CommonResponseDto commonResponseDto = iDishHandler.saveDish(DishRequestDto);

        if (!commonResponseDto.getStatus())
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(commonResponseDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(commonResponseDto);
    }

    @Operation(summary = "Update a Dish. You can only update price and description.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dish Updated.", content = @Content),
            @ApiResponse(responseCode = "202", description = "Request accepted but unsuccessful.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Dish not found.", content = @Content)
    })
    @PutMapping("/")
    public ResponseEntity<CommonResponseDto> updateDish(@Valid  @RequestBody DishUpdateRequestDto dishUpdateRequestDto) {
        CommonResponseDto commonResponseDto = iDishHandler.updateDish(dishUpdateRequestDto);

        if (!commonResponseDto.getStatus())
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(commonResponseDto);

        return ResponseEntity.status(HttpStatus.OK)
                .body(commonResponseDto);
    }

    @Operation(summary = "Enable/Disable a Dish.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dish Updated.", content = @Content),
            @ApiResponse(responseCode = "202", description = "Request accepted but unsuccessful.", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized user", content = @Content),
            @ApiResponse(responseCode = "404", description = "Dish not found.", content = @Content)
    })
    @PutMapping("/active")
    public ResponseEntity<CommonResponseDto> activeDish(@Valid  @RequestBody ActiveDishRequestDto activeDishRequestDto) {
        CommonResponseDto commonResponseDto = iDishHandler.activeDish(activeDishRequestDto);

        if (!commonResponseDto.getStatus())
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(commonResponseDto);

        return ResponseEntity.status(HttpStatus.OK)
                .body(commonResponseDto);
    }

    @Operation(summary = "Find all dishes by restaurant and category.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content),
            @ApiResponse(responseCode = "202", description = "Request accepted but unsuccessful.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found.", content = @Content)
    })
    @GetMapping("/restaurant/{idRestaurant}/category/{idCategory}")
    public ResponseEntity<List<DishResponseDto>> getDishesByRestaurantAndCategory(@PathVariable Long idRestaurant,
                                                                                  @PathVariable Long idCategory,
                                                                                  @RequestParam(defaultValue = "1") Integer page,
                                                                                  @RequestParam(defaultValue = "10") Integer size) {

        return ResponseEntity.ok(
                iDishHandler.getDishesByRestaurantAndCategory( idRestaurant, idCategory,
                        PageRequest.of( page-1, size, Sort.by(Sort.Direction.ASC, "name") )
                )
        );

    }

}