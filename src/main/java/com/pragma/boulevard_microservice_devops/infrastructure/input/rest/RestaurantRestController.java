package com.pragma.boulevard_microservice_devops.infrastructure.input.rest;

import com.pragma.boulevard_microservice_devops.application.dto.request.RestaurantRequestDto;
import com.pragma.boulevard_microservice_devops.application.dto.response.CommonResponseDto;
import com.pragma.boulevard_microservice_devops.application.dto.response.RestaurantPageableResponseDto;
import com.pragma.boulevard_microservice_devops.application.handler.IRestaurantHandler;
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
@RequestMapping("/api/v1/restaurant")
@RequiredArgsConstructor
public class RestaurantRestController {

    private final IRestaurantHandler iRestaurantHandler;

    @Operation(summary = "Add a new Restaurant.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Restaurant created.", content = @Content),
            @ApiResponse(responseCode = "202", description = "Request accepted but unsuccessful.", content = @Content),
            @ApiResponse(responseCode = "409", description = "Restaurant already exists.", content = @Content)
    })
    @PostMapping("/")
    public ResponseEntity<CommonResponseDto> saveRestaurant(@Valid @RequestBody RestaurantRequestDto restaurantRequestDto) {
        CommonResponseDto commonResponseDto = iRestaurantHandler.saveRestaurant(restaurantRequestDto);

        if (!commonResponseDto.getStatus())
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(commonResponseDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(commonResponseDto);
    }

    @Operation(summary = "Find all restaurants.")
            @ApiResponses(value = {
                    @ApiResponse(responseCode = "200", description = "OK", content = @Content),
                    @ApiResponse(responseCode = "202", description = "Request accepted but unsuccessful.", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Not found.", content = @Content)
    })
    @GetMapping("all")
    public ResponseEntity<List<RestaurantPageableResponseDto>> getAllRestaurants(@RequestParam(defaultValue = "1") Integer page,
                                                                                 @RequestParam(defaultValue = "10") Integer size) {
        return ResponseEntity.ok(
                iRestaurantHandler.getAllRestaurants(
                        PageRequest.of( page-1, size, Sort.by(Sort.Direction.ASC, "name") )
                )
        );
    }

}