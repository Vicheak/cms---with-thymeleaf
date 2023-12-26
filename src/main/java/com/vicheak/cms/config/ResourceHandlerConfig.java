package com.vicheak.cms.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ResourceHandlerConfig implements WebMvcConfigurer {

    @Value("${file.server-path}")
    private String serverPath;

    @Value("${file.client-path}")
    private String clientPath;

    @Value("${resource.server-path}")
    private String resourceServerPath;

    @Value("${resource.client-path}")
    private String resourceClientPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //configure file system resources
        registry.addResourceHandler(clientPath)
                .addResourceLocations("file:" + serverPath);

        //configure classpath resources
        registry.addResourceHandler(resourceClientPath)
                .addResourceLocations("classpath:" + resourceServerPath);
    }

}
