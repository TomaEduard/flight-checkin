package com.example.flightcheckin.integration;

import com.example.flightcheckin.integration.dto.Reservation;
import com.example.flightcheckin.integration.dto.ReservationUpdateRequest;

public interface ReservationRestClient {

	public Reservation findReservation(Long id);

	public Reservation updateReservation(ReservationUpdateRequest request);

}
