package com.example.flightcheckin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.flightcheckin.integration.ReservationRestClient;
import com.example.flightcheckin.integration.dto.Reservation;
import com.example.flightcheckin.integration.dto.ReservationUpdateRequest;

@Controller
public class CheckInController {

	@Autowired
	ReservationRestClient reservationRestClient;
	
	@RequestMapping("/showStartCheckIn")
	public String showStartCheckIn() {
		
		return "startCheckIn";
	}
	
	@RequestMapping("/startCheckIn")
	public String startCheckIn(@RequestParam("reservationId") Long reservationId, ModelMap modelMap) {
		
		Reservation reservation = reservationRestClient.findReservation(reservationId);
		modelMap.addAttribute("reservation", reservation);
		
		return "displayReservationDetails";
	}
	
	@RequestMapping("/completeCheckIn")
	public String completeCheckIn(@RequestParam("reservationId") Long reservationId,
								  @RequestParam("numberOfBags") int numberOfBags) {
		
		ReservationUpdateRequest reservationUpdateRequest = new ReservationUpdateRequest();
		
		reservationUpdateRequest.setId(reservationId);
		reservationUpdateRequest.setCheckedIn(true);
		reservationUpdateRequest.setNumberOfBags(numberOfBags);
		reservationRestClient.updateReservation(reservationUpdateRequest);
		
		return "checkInConfirmation";
	}

}
