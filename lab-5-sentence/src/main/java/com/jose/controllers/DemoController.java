package com.jose.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class DemoController {
	
	@Autowired
	private LoadBalancerClient loadBalancerClient;
	
	 @RequestMapping("/sentence")
	  public @ResponseBody String getSentence() {
	    return 
	      getWord("LAB-5-SUBJECT") + " "
	      + getWord("LAB-5-VERB") + " "
	      + getWord("LAB-5-ARTICLE") + " "
	      + getWord("LAB-5-ADJECTIVE") + " "
	      + getWord("LAB-5-NOUN") + "."
	      ;	
	  }	 
	 public String getWord(String service) {
		    final ServiceInstance serviceInstance = loadBalancerClient.choose(service);
		    final URI uri = serviceInstance.getUri();
		    return (new RestTemplate()).getForObject(uri, String.class);			    
	 }
}
		   


