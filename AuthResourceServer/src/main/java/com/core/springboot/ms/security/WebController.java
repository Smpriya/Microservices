package com.core.springboot.ms.security;

import java.util.UUID;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/foo")
public class WebController {
	
	@RequestMapping(method = RequestMethod.GET)
    public String readApp() {
		System.out.println("WebController::readApp");
        return "read App " + UUID.randomUUID().toString();
    }

    @PreAuthorize("hasAuthority('FOO_WRITE')")
    @RequestMapping(method = RequestMethod.POST)
    public String writeApp() {
        return "write App " + UUID.randomUUID().toString();
    }

}
