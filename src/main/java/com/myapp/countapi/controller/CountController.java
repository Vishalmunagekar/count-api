package com.myapp.countapi.controller;

import com.myapp.countapi.models.CountResponse;
import com.myapp.countapi.service.CounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

@RestController
@RequestMapping("/count")
public class CountController {

    @Autowired
    private CounterService counterService;

    @GetMapping("/hello")
    public String hello(){
        return  "Hello! welcome to count-api";
    }

    @GetMapping("/create")
    public ResponseEntity<CountResponse> createCount(@RequestParam("username") String username) {
        return new ResponseEntity<>(counterService.generateKeyForNewUser(username), HttpStatus.CREATED);
    }

    @GetMapping("/get")
    public ResponseEntity<CountResponse> getCount(@RequestParam("key") String key, @RequestParam(value = "counter", defaultValue = "1") int counter) {
        return new ResponseEntity<>(counterService.increaseCount(key, BigInteger.valueOf(counter)), HttpStatus.OK);
    }

    @GetMapping("/reset")
    public ResponseEntity<CountResponse> resetCount(@RequestParam("key") String key) {
        return new ResponseEntity<>(counterService.resetCount(key), HttpStatus.OK);
    }

    @GetMapping("/forgot-key")
    public ResponseEntity<CountResponse> forgotKey(@RequestParam("username") String username) {
        return new ResponseEntity<>(counterService.forgotKeyForExistingUser(username), HttpStatus.CREATED);
    }
}
