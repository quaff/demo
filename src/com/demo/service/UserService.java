package com.demo.service;

import org.ironrhino.core.remoting.Remoting;
import org.ironrhino.core.spring.security.ConcreteUserDetailsService;

@Remoting
public interface UserService extends ConcreteUserDetailsService {

}
