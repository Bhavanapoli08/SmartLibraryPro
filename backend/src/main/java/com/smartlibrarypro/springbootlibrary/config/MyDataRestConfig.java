package com.smartlibrarypro.springbootlibrary.config;

import com.smartlibrarypro.springbootlibrary.entity.Book;
import com.smartlibrarypro.springbootlibrary.entity.Message;
import com.smartlibrarypro.springbootlibrary.entity.Review;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {

    private static final String ALLOWED_ORIGINS = "http://localhost:3000";

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        HttpMethod[] unsupportedActions = {
                HttpMethod.POST,
                HttpMethod.PATCH,
                HttpMethod.DELETE,
                HttpMethod.PUT
        };

        // Expose entity IDs
        config.exposeIdsFor(Book.class, Review.class, Message.class);

        // Disable HTTP methods for these entities
        disableHttpMethods(Book.class, config, unsupportedActions);
        disableHttpMethods(Review.class, config, unsupportedActions);
        disableHttpMethods(Message.class, config, unsupportedActions);

        // Configure CORS
        cors.addMapping(config.getBasePath() + "/**")
                .allowedOrigins(ALLOWED_ORIGINS)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }

    private void disableHttpMethods(Class<?> clazz,
                                    RepositoryRestConfiguration config,
                                    HttpMethod[] unsupportedActions) {
        config.getExposureConfiguration()
                .forDomainType(clazz)
                .withItemExposure((metadata, httpMethods) ->
                        httpMethods.disable(unsupportedActions))
                .withCollectionExposure((metadata, httpMethods) ->
                        httpMethods.disable(unsupportedActions));
    }
}
