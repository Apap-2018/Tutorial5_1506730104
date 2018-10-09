package com.apap.tutorial5.service;

import com.apap.tutorial5.model.FlightModel;
//import com.apap.tutorial4.model.PilotModel;
import com.apap.tutorial5.repository.FlightDB;
//import com.apap.tutorial4.repository.PilotDB;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* 
* FlightServiceImpl
*
*/
@Service
@Transactional
public class FlightServiceImpl implements FlightService{
	@Autowired
	private FlightDB flightDb;
	/**
	 * @Autowired
	private FlightDB pilotDb;
	 */
	

	@Override
	public void addFlight(FlightModel flight) {
		flightDb.save(flight);
	}

	@Override
	public FlightModel deleteFlight(String flightNumber) {
		FlightModel deleted = flightDb.findByFlightNumber(flightNumber);
		flightDb.delete(flightDb.findByFlightNumber(flightNumber));
		return deleted;
	}

	@Override
	public FlightModel updateFlight(String flightNumber, String origin, String destination, Date time) {
		flightDb.findByFlightNumber(flightNumber).setOrigin(origin);
		flightDb.findByFlightNumber(flightNumber).setDestination(destination);
		flightDb.findByFlightNumber(flightNumber).setTime(time);
		//flightDb.findByFlightNumber(flightNumber).setPilot(pilotDb.findByLicenseNumber(licenseNumber));
		FlightModel updated = flightDb.findByFlightNumber(flightNumber);
		return updated;
	}

	@Override
	public FlightModel getFlightDetailByFlightNumber(String flightNumber) {
		return flightDb.findByFlightNumber(flightNumber);
	}
	/**
	 * 
	 * @Override
	public PilotModel getPilotDetailByLicenseNumber(String licenseNumber) {
		return pilotDb.findByLicenseNumber(licenseNumber);
	}
	 */
}
