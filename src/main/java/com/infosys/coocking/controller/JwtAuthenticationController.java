//package com.infosys.coocking.controller;
//
//import com.energizeglobal.shopping.config.SwaggerConfig;
//import com.energizeglobal.shopping.model.dto.JwtRequest;
//import com.energizeglobal.shopping.model.dto.JwtResponse;
//import com.energizeglobal.shopping.model.dto.UserDto;
//import com.energizeglobal.shopping.service.user.JwtUserDetailsService;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping(path = "auth/", produces = MediaType.APPLICATION_JSON_VALUE)
//@CrossOrigin
//@Api(tags = {SwaggerConfig.JWT_AUTHENTICATION_CONTROLLER_TAG})
//public class JwtAuthenticationController {
//
//    private JwtUserDetailsService userDetailsService;
//
//    @Autowired
//    public JwtAuthenticationController(JwtUserDetailsService userDetailsService) {
//        this.userDetailsService = userDetailsService;
//    }
//
//    @PostMapping(value = "login")
//    @ApiOperation(value = "Endpoint for login and getting token",
//            produces = MediaType.APPLICATION_JSON_VALUE,
//            response = JwtResponse.class,
//            httpMethod = "POST")
//    public JwtResponse createAuthenticationToken(@RequestBody JwtRequest request) {
//        return userDetailsService.login(request);
//    }
//
//    @PostMapping("register")
//    @ApiOperation(value = "Endpoint for registering the user",
//            produces = MediaType.APPLICATION_JSON_VALUE,
//            response = Long.class,
//            httpMethod = "POST")
//    public Long register(@RequestBody UserDto request) {
//        return userDetailsService.register(request);
//    }
//}
//
