package com.reactive.demo.controller;

import com.reactive.demo.dto.UserDTO;
import com.reactive.demo.service.UserService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public Flux<UserDTO> findAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<UserDTO> findById(@PathVariable String id) {
        return userService.findById(id);
    }

    @PostMapping
    public Mono<UserDTO> save(@RequestBody UserDTO dto) {
        return userService.save(dto);
    }
}
