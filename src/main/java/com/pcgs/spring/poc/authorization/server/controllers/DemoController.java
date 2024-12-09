package com.pcgs.spring.poc.authorization.server.controllers;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping("/demo")
    public String demo() {
        var authenticationInfo = SecurityContextHolder.getContext().getAuthentication();
        for (GrantedAuthority authority : authenticationInfo.getAuthorities()) {
            System.out.println(authority);
        }
        return "Demo";
    }
}
