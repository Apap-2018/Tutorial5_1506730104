package com.apap.tutorial5.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apap.tutorial5.model.FlightModel;
import com.apap.tutorial5.repository.FlightDb;

@Service
@Transactional
public class FlightServiceImpl implements FlightService{

	@Autowired
	private FlightDb flightDb;
	
	@Override
	public void addFlight(FlightModel flight) {
		// TODO Auto-generated method stub
		flightDb.save(flight);
		
	}

	@Override
	public void deletePilot(Long id) {
		// TODO Auto-generated method stub
		flightDb.delete(flightDb.findByFlightId(id));
	}

}
