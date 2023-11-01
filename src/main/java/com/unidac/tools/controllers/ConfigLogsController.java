package com.unidac.tools.controllers;


import com.unidac.tools.entities.ConfigLogs;
import com.unidac.tools.services.ConfigServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api/config/logs")
@RequiredArgsConstructor
public class ConfigLogsController {

    private final ConfigServices configServices;

    @GetMapping("/find-by-id/{user}")
    public ResponseEntity<ConfigLogs> findByUserConfig(@PathVariable String user) {
        return new ResponseEntity<>(configServices.findByUserConfig(user), HttpStatus.OK);
    }

    @PostMapping("/save")
    public void save(@RequestBody ConfigLogs configLogs) {
        configServices.save(configLogs);
    }

}
