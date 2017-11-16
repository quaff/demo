package com.demo.api;

import org.ironrhino.rest.ApiConfigBase;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ControllerAdvice;

//only for exclude-filter of root ctx
@ControllerAdvice
@Configuration
@ComponentScan
public class ApiConfig extends ApiConfigBase {

}