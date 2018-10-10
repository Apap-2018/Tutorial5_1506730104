package com.apap.tutorial5.controller;

import com.apap.tutorial5.model.PilotModel;
import com.apap.tutorial5.service.PilotService;
import com.apap.tutorial5.model.FlightModel;
import com.apap.tutorial5.service.FlightService;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * FlightController
 *
 */
@Controller
public class FlightController {
	@Autowired
	private FlightService flightService;
	
	@Autowired
	private PilotService pilotService;
	
	@RequestMapping(value="/flight/add/{licenseNumber}", method = RequestMethod.GET)
	private String add(@PathVariable(value = "licenseNumber") String licenseNumber, Model model) {
		FlightModel flight = new FlightModel();
		PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		flight.setPilot(pilot);
		
		model.addAttribute("flight", flight);
		return "addFlight";
	}
	
	@RequestMapping(value="/flight/add", method = RequestMethod.POST)
	private String addFlightSubmit(@ModelAttribute FlightModel flight) {
		flightService.addFlight(flight);
		return "add";
	}
	
	@RequestMapping(value = "flight/delete",method=RequestMethod.POST)
	public String deleteFlight(@RequestParam ("flightNumber") String flightNumber, Model model) {
		FlightModel deleted = flightService.deleteFlight(flightNumber);
		model.addAttribute("flight", deleted);
		model.addAttribute("pilot", deleted.getPilot());
		return "delete-flight";
	}
	
	@RequestMapping(value = "/flight/update", method=RequestMethod.POST)
	public String updateFlight(@RequestParam ("flightNumber") String flightNumber, @RequestParam ("origin") String origin, @RequestParam ("destination") String destination, @RequestParam ("time") Date time, Model model) {
		FlightModel updated = flightService.updateFlight(flightNumber, origin, destination, time);
		model.addAttribute("flight", updated);
		model.addAttribute("pilot", updated.getPilot());
		return "update-flight";
	}
	
	@RequestMapping(value = "flight/view-flight", method=RequestMethod.GET)
	public String viewFlight(@RequestParam ("flightNumber") String flightNumber, Model model){
		FlightModel flight = flightService.getFlightDetailByFlightNumber(flightNumber);
		model.addAttribute("flight", flight);
		model.addAttribute("pilot", flight.getPilot());
		return "view-flight";
	}
}
