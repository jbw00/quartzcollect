package com.hamusuta.quartzcollect.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class HttpJobConstants {

    @Value("${job.http.baidu}")
    public String url;
}
