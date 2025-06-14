package com.avi.batch.prod;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty( name = "avi.class.enabled",havingValue = "true")
public class ProdLogger {

    public String getLogger(){
        return "success from Prod Logger";
    }
}
