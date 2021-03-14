package net.openobject.ekko.sample.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class CloudConfigController {
	
    @Value("${database.username}")
    private String dbUsername;
    @Value("${database.password}")
    private String dbPassword;

    @GetMapping("/cloud/config")
    public String getCloudConfig() {
        return String.format("username: %s, password: %s", dbUsername, dbPassword);
    }
	
}
