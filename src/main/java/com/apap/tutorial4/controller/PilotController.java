package com.apap.tutorial5.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.apap.tutorial5.model.FlightModel;
import com.apap.tutorial5.model.PilotModel;
import com.apap.tutorial5.service.FlightService;
import com.apap.tutorial5.service.PilotService;

@Controller
public class PilotController {

	@Autowired
	private PilotService pilotService;
	
	@RequestMapping("/")
	private String home() {
		String pageTitle = "Home";
		model.addAttribute("pageTitle", pageTitle);
		return "home";
	}
	
	@RequestMapping(value="/pilot/add", method=RequestMethod.GET)
	private String add(Model model) {
		String pageTitle = "Add Pilot";
		model.addAttribute("pageTitle", pageTitle);
		model.addAttribute("pilot", new PilotModel());
		return "addPilot";
	}
	
	@RequestMapping(value="/pilot/add", method=RequestMethod.POST)
	private String addPilotSubmit(@ModelAttribute PilotModel pilot) {
		pilotService.addPilot(pilot);
		String pageTitle = "Add";
		model.addAttribute("pageTitle", pageTitle);
		return "add";
	}
	
	@RequestMapping(value = "/pilot/view", method = RequestMethod.GET)
	private String viewPilot(@RequestParam("licenseNumber") String licenseNumber, Model model) {
		PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		List<FlightModel> listOfFlight = pilot.getPilotFlight();
		String pageTitle = "View Pilot";
		model.addAttribute("pageTitle", pageTitle);
		model.addAttribute("listOfFlight", listOfFlight);
		model.addAttribute("pilot", pilot);
		return "view-pilot";
	}
	
	@RequestMapping("/pilot/delete")
	public String delete(@RequestParam("licenseNumber") String licenseNumber, Model model) {
		PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		String pageTitle = "Delete Pilot";
		model.addAttribute("pageTitle", pageTitle);
		pilotService.deletePilot(pilot);
		return "delete-pilot";
	}
	
	@RequestMapping(value="/pilot/update", method=RequestMethod.GET)
	public String update(@RequestParam("licenseNumber") String licenseNumber, Model model) {
		PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		String pageTitle = "Update Pilot";
		model.addAttribute("pageTitle", pageTitle);
		if(pilot != null) {
			model.addAttribute("pilot", pilot);
			return "updatePilot";
		} else {
			return "not-found";
		}
		
	}
	
	@RequestMapping(value="/pilot/update", method=RequestMethod.POST)
	private String updatePilotSubmit(@ModelAttribute PilotModel pilot) {
		String pageTitle = "Update";
		model.addAttribute("pageTitle", pageTitle);
		pilotService.addPilot(pilot);
		return "update";
	}
	
	
	
}
