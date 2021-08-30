package com.cegeka.dplt.hazelcast.rest;

import com.cegeka.dplt.hazelcast.cache.CacheClient;
import com.cegeka.dplt.hazelcast.cache.Car;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/cars")
public class Controller {

    private final CacheClient cacheClient;

    public Controller(CacheClient cacheClient) {
        this.cacheClient = cacheClient;
    }

    @PostMapping(value = "/{number}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.CREATED)
    public Car put(@RequestBody Car car, @PathVariable String number) {
        if (car.getColor()==null || car.getBrand()==null) {
            throw new BadRequestException("not valid car");
        }
        return cacheClient.put(number, car);
    }

    @GetMapping(value = "/{number}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Car get(@PathVariable String number) {
        Car car= cacheClient.get(number);
        if (car==null) {
            throw new NotFoundException("car not found");
        }
        return car;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public class NotFoundException extends RuntimeException{
        public NotFoundException(String message) {
            super(message);
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public class BadRequestException extends RuntimeException {
        public BadRequestException(String message) {
            super(message);
        }
    }

}

