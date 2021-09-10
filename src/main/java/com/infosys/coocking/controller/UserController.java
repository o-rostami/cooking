package com.infosys.coocking.controller;


import com.infosys.coocking.config.SwaggerConfig;
import com.infosys.coocking.service.user.JwtUserDetailsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/")
@Api(tags = {SwaggerConfig.USER_CONTROLLER_TAG})
public class UserController {

    private JwtUserDetailsService userDetailsService;

    @Autowired
    public UserController(JwtUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @GetMapping(value = "block/{userId}")
    @ApiOperation(value = "Endpoint for blocking the user",
            produces = MediaType.APPLICATION_JSON_VALUE,
            httpMethod = "GET")
    public void blockUser(
            @ApiParam(value = "The id of user must be present. It can't be empty or null",
                    required = true)
            @PathVariable(value = "userId") Long userId) {
        userDetailsService.blockUser(userId);
    }

    @GetMapping(value = "unblock/{userId}")
    @ApiOperation(value = "Endpoint for blocking the user",
            produces = MediaType.APPLICATION_JSON_VALUE,
            httpMethod = "GET")
    public void unBlockUser(
            @ApiParam(value = "The id of user must be present. It can't be empty or null",
                    required = true)
            @PathVariable(value = "userId") Long userId) {
        userDetailsService.unBlockUser(userId);
    }

}
