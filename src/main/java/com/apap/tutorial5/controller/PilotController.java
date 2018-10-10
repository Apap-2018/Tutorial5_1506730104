package com.apap.tutorial5.controller;

import com.apap.tutorial5.model.PilotModel;
import com.apap.tutorial5.service.PilotService;

import java.util.Optional;

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
 * PilotController
 *
 */
@Controller
public class PilotController {
	@Autowired
	private PilotService pilotService;
	
	@RequestMapping("/")
	private String home() {
		return "home";
	}
	
	@RequestMapping(value="/pilot/add", method = RequestMethod.GET)
	private String add(Model model) {
		model.addAttribute("pilot", new PilotModel());
		return "addPilot";
	}
	
	@RequestMapping(value="/pilot/add", method = RequestMethod.POST)
	private String addPilotSubmit(@ModelAttribute PilotModel pilot) {
		pilotService.addPilot(pilot);
		return "add";
	}
	
	@RequestMapping(value = "pilot/view", method = RequestMethod.GET)
	private String viewPilot(@RequestParam("licenseNumber") String licenseNumber, Model model){
		PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		model.addAttribute("pilot", pilot);
		model.addAttribute("pilotFlight", pilot.getPilotFlight());
		return "view-pilot";
	}
	
	@RequestMapping(value = "pilot/delete", method = RequestMethod.POST)
	private String deletePilot(@RequestParam("licenseNumber") String licenseNumber, Model model) {
		PilotModel deleted = pilotService.deletePilot(licenseNumber);
		model.addAttribute("pilot", deleted);
		return "delete-pilot";
	}
	
	@RequestMapping(value = "/pilot/update", method = RequestMethod.POST)
	public String updatePilot(@RequestParam("licenseNumber") String licenseNumber, @RequestParam("name") String name, @RequestParam("flyHour") int flyHour, Model model) {
		PilotModel updated = pilotService.updatePilot(licenseNumber, name, flyHour);
		model.addAttribute("pilot", updated);
		return "update-pilot";
	}
}
