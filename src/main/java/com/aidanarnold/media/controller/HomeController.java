package com.aidanarnold.media.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);

	/**
	 * Serves up the default landing page
	 * @return home
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
	public String home() {
		logger.info("Displaying landing page");
		return "Yo! I am a Work In Progress.";
	}

}
