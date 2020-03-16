package com.ics.greeting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.logging.Logger;

@RestController
@RequestMapping("/greeting")
public class GreetingController {

    private static final Logger LOG = Logger.getLogger(GreetingController.class.getName());
    @Autowired
    private GreetingProperties properties;
    @GetMapping
    public String getGreeting(){
        LOG.info("Greeting: " + properties.getGreeting());
        return properties.getGreeting();
    }

    @RequestMapping("/{languageCode}")
    public String getGreeting(@PathVariable String languageCode){
        LOG.info("Language Code: " + languageCode);
        LOG.info("Greeting: " + properties.getGreetings().get(languageCode.toUpperCase()));
        return properties.getGreetings().getOrDefault(languageCode.toUpperCase(), properties.getGreeting());
    }
}
