package com.example.api.manufacturer.api;

import com.example.api.manufacturer.dto.ManufacturerDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@FeignClient(url="http//localhost:8080/internal/api/v1/manufacturer",name="ManufacturerGateway")
public interface ManufacturerGateway {

    @GetMapping
    List<ManufacturerDto> getManufacturerList();

    @GetMapping("/{manufacturerId}")
    ResponseEntity<ManufacturerDto> getManufacturer(@PathVariable("manufacturerId") Long id);

    @PostMapping
    ResponseEntity<ManufacturerDto> handlePost(@Validated @RequestBody ManufacturerDto manufacturerDto);

    @PutMapping("/{manufacturerId}")
    ResponseEntity<ManufacturerDto> handleUpdate(@PathVariable("manufacturerId") Long id,
                                                 @Validated @RequestBody ManufacturerDto manufacturerDto);

    @DeleteMapping("/{manufacturerId}")
    void deleteById(@PathVariable("manufacturerId") Long id);
}
