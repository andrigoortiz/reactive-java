package com.reactive.demo.service;

import com.reactive.demo.converter.UserConverter;
import com.reactive.demo.dto.UserDTO;
import com.reactive.demo.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {

    private final UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public Flux<UserDTO> findAll() {
        return repo.findAll()
                .map(UserConverter::toDTO);
    }

    public Mono<UserDTO> findById(String id) {
        //TODO usar flatmap
        return repo.findById(id)
                .map(UserConverter::toDTO)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found")));
    }

    public Mono<UserDTO> save(UserDTO dto) {
        return repo.save(UserConverter.toEntity(dto))
                .map(UserConverter::toDTO);
    }
}
