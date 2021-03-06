package com.company.restservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * This spring controller class renders and displays the home page of the application.
 *
 * @author Simon Njenga
 * @since 0.1
 */
@Controller
public class HomePageController {

    @RequestMapping(value = "/homePage.htm", method = RequestMethod.GET)  
    public String getHomePage() {
        return "companyManagement";
    }
}