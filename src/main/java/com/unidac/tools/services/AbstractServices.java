package com.unidac.tools.services;

import com.unidac.tools.mapper.LogsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


abstract class AbstractServices {
    protected final Logger logger = LoggerFactory.getLogger(AbstractServices.class);

    @Autowired
    protected LogsMapper logsMapper;


}
