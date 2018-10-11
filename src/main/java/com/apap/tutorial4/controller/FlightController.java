package com.apap.tutorial5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.apap.tutorial5.model.FlightModel;
import com.apap.tutorial5.model.PilotModel;
import com.apap.tutorial5.service.FlightService;
import com.apap.tutorial5.service.PilotService;

@Controller
public class FlightController {

	@Autowired
	private FlightService flightService;
	
	@Autowired
	private PilotService pilotService;
	
	@RequestMapping(value="/flight/add/{licenseNumber}", method=RequestMethod.GET)
	private String add(@PathVariable(value="licenseNumber") String licenseNumber, Model model) {
		FlightModel flight = new FlightModel();
		PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		flight.setPilot(pilot);
		
		String pageTitle = "Add Flight";
		model.addAttribute("pageTitle", pageTitle);
		model.addAttribute("flight", flight);
		return "addFlight";
	}
	
	@RequestMapping(value="/flight/add", method=RequestMethod.POST)
	private String addFlightSubmit(@ModelAttribute FlightModel flight) {
		flightService.addFlight(flight);
		String pageTitle = "Add";
		model.addAttribute("pageTitle", pageTitle);
		return "add";
	}
	
	@RequestMapping(value="/flight/delete", method=RequestMethod.POST)
	private String deleteFlight(@ModelAttribute PilotModel pilot, Model model) {
		for(FlightModel flight : pilot.getPilotFlight()) {
			flightService.deleteFlightById(flight.getId());
		}
		String pageTitle = "Delete Flight";
		model.addAttribute("pageTitle", pageTitle);
		return "delete-flight";
	}
}
