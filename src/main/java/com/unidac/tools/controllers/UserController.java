package com.unidac.tools.controllers;


import com.unidac.tools.dto.ResponseDTO;
import com.unidac.tools.dto.UserDTO;
import com.unidac.tools.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<ResponseDTO> create(@RequestBody @Valid UserDTO userDTO){
        return userService.create(userDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> findById(@PathVariable @Valid String id){
        return userService.findById(id);
    }

    @GetMapping
    public ResponseEntity<ResponseDTO> findAll(){
        return userService.findAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> update(@RequestBody @Valid UserDTO userDTO, @PathVariable String id){
        return userService.update(userDTO,id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> delete(@PathVariable String id){
        return userService.delete(id);
    }

}
