package com.apap.tutorial5.service;

import com.apap.tutorial5.model.PilotModel;
import com.apap.tutorial5.repository.PilotDB;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * PilotServiceImpl
 *
 */
@Service
@Transactional
public class PilotServiceImpl implements PilotService{
	@Autowired
	private PilotDB pilotDb;
	
	@Override
	public PilotModel getPilotDetailByLicenseNumber(String licenseNumber) {
		return pilotDb.findByLicenseNumber(licenseNumber);
	}

	@Override
	public void addPilot(PilotModel pilot) {
		pilotDb.save(pilot);
	}

	@Override
	public PilotModel deletePilot(String licenseNumber) {
		PilotModel deleted = pilotDb.findByLicenseNumber(licenseNumber);
		pilotDb.delete(pilotDb.findByLicenseNumber(licenseNumber));
		return deleted;
	}

	@Override
	public PilotModel updatePilot(String licenseNumber, String name, int flyHour) {
		pilotDb.findByLicenseNumber(licenseNumber).setName(name);
		pilotDb.findByLicenseNumber(licenseNumber).setFlyHour(flyHour);
		PilotModel updated = pilotDb.findByLicenseNumber(licenseNumber);
		return updated;
	}
}
