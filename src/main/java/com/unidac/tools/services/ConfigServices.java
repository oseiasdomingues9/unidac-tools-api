package com.unidac.tools.services;


import com.unidac.tools.repositories.ConfigLogsRepository;
import com.unidac.tools.entities.ConfigLogs;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.logging.Logger;


@Service
@RequiredArgsConstructor
public class ConfigServices {

    private static final Logger LOGGER = Logger.getLogger("ListenerBean");

    private final ConfigLogsRepository configLogsRepository;


    public ConfigLogs findByUserConfig(String userId) {
        ConfigLogs configLogs = configLogsRepository.findByUserId(userId);
        if(configLogs == null){
            ConfigLogs config = new ConfigLogs();
            config.setIntegrationId(true);
            config.setRefId1(true);
            config.setRefName1(true);
            config.setRefId2(true);
            config.setRefName2(true);
            config.setMessage(true);
            config.setTime(true);
            config.setRequestMethod(true);
            config.setContentType(true);
            config.setDebugMode (true);
            config.setUserId(userId);
            configLogsRepository.save(config);
            return config;
        }else {
            System.out.println(userId);
            return configLogs;
        }

    }
    public void save(ConfigLogs configLogs) {
        ConfigLogs configLogs1 = configLogsRepository.findByUserId(configLogs.getUserId());
        if (configLogs1 != null){
            configLogs1.setIntegrationId(configLogs.isIntegrationId());
            configLogs1.setRefId1(configLogs.isRefId1());
            configLogs1.setRefName1(configLogs.isRefName1());
            configLogs1.setRefId2(configLogs.isRefId2());
            configLogs1.setRefName2(configLogs.isRefName2());
            configLogs1.setMessage(configLogs.isMessage());
            configLogs1.setTime(configLogs.isTime());
            configLogs1.setRequestMethod(configLogs.isRequestMethod());
            configLogs1.setContentType(configLogs.isContentType());
            configLogs1.setDebugMode(configLogs.isDebugMode());
            configLogsRepository.save(configLogs1);
        }
    }
}
