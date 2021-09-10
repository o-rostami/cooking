package com.infosys.coocking.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;


/**
 * A <i>SwaggerConfig</i>. This class has responsibility to configure the swagger for exposing the apis<p>
 *
 * @author Omid Rostami
 */


@Configuration
@EnableSwagger2
public class SwaggerConfig {

    public static final String JWT_AUTHENTICATION_CONTROLLER_TAG = "user controller for login and register";
    public static final String RECIPE_CONTROLLER_TAG = "user controller for blocking and unblocking";
    public static final String USER_CONTROLLER_TAG = "user controller for blocking and unblocking";
    public static final String PRODUCT_CONTROLLER_TAG = "product controller";
    public static final String COMMENT_CONTROLLER_TAG = "comment controller";
    public static final String RATE_CONTROLLER_TAG = "rate controller";
    public static final String PRODUCT_PER_CATEGORY_CONTROLLER_TAG = "product per category controller";


    @Bean
    public Docket api() {

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(getApiInfo());
    }

    private ApiInfo getApiInfo() {
        return new ApiInfo(
                "EGS",
                "This application provides api(s) to create and maintain services",
                "1.0",
                "all right reserved to",
                new Contact("Omid Rostami", "", "rostami.od@gmail.com"),
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                Collections.emptyList()
        );
    }

}
