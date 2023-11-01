package com.unidac.tools.controllers;

import com.unidac.tools.dto.AuthenticationDTO;
import com.unidac.tools.dto.ResponseDTO;
import com.unidac.tools.dto.UserDTO;
import com.unidac.tools.dto.LoginResponseDTO;
import com.unidac.tools.services.AuthorizationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthorizationService authorizationService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthenticationDTO loginDTO) {
        return authorizationService.checkLogin(loginDTO);
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout() {
        return authorizationService.logout();
    }


}
