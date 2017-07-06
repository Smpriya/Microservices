package com.core.springboot.ms.eurekaclient;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableDiscoveryClient
@SpringBootApplication
public class EurekaClientApplication {
	
	public static void main(String args[]) {
		SpringApplication.run(EurekaClientApplication.class, args);
	}
}

@RestController
class ServiceInstanceRestController {
	private CallBackClient callBackClient;
	
	private DiscoveryClient discoveryClient;
	
	@Autowired
	public void serviceInstanceRestController (CallBackClient callBackClient, DiscoveryClient discoveryClient) {
		this.callBackClient = callBackClient;
		this.discoveryClient = discoveryClient;
	}
	
	@RequestMapping("/")
    public String index() {
        return
                "<ul>" +
                   "<li><a href=\"/callback\">callback</a>" +
                   "<li><a href=\"/instances\">instances</a>" +
                "</ul>";
    }

    @RequestMapping("/instances")
    public List<ServiceInstance> clients() {
        return this.discoveryClient.getInstances(callBackClient.springApplicationName);
    }

    @RequestMapping("/callback")
    public CallBackClient callBackClient() {
        return callBackClient;
    }
}
	
@Component
class CallBackClient {
    @Value("${spring.application.name}")
    public String springApplicationName;

    @Value("${server.port:8080}")
    public String serverPort;
}
