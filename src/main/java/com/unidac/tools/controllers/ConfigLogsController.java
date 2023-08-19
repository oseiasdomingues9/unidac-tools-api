package com.unidac.tools.controllers;


import com.unidac.tools.entities.ConfigLogs;
import com.unidac.tools.services.ConfigServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/config/logs")
@RequiredArgsConstructor
public class ConfigLogsController {

    private final ConfigServices configServices;

    @GetMapping("/find-by-username/{username}")
    public ResponseEntity<ConfigLogs> findByUserConfig(String username) {
        return new ResponseEntity<>(configServices.findByUserConfig(username), HttpStatus.OK);
    }

    @PostMapping("/save")
    public void save(ConfigLogs configLogs) {
        configServices.save(configLogs);
    }

}
