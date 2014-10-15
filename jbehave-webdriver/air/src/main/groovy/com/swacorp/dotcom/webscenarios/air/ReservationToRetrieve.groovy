package com.swacorp.dotcom.webscenarios.air

import domain.AirReservation
import domain.Passenger

class ReservationToRetrieve {

    private String recordLocator
    private String passengerFirstName
    private String passengerLastName

    public ReservationToRetrieve() {

    }

    public ReservationToRetrieve(String recordLocator, String passengerFirstName, String passengerLastName) {
        this.recordLocator = recordLocator
        this.passengerFirstName = passengerFirstName
        this.passengerLastName = passengerLastName
    }

    def ReservationToRetrieve getAdultOrSeniorReservation(AirReservation airReservation) {

        Passenger passenger = null
        String recordLocator = null

        if (airReservation.containsAdultPassenger()) {
            passenger = airReservation.getAdultPassengers().get(0)
            recordLocator = airReservation.adultPnr
        }
        if (airReservation.containsSeniorPassenger()) {
            passenger = airReservation.getSeniorPassengers().get(0)
            recordLocator = airReservation.seniorPnr
        }

        return new ReservationToRetrieve(recordLocator, passenger.firstName, passenger.lastName)
    }

    String getRecordLocator() {
        return recordLocator
    }

    String getPassengerFirstName() {
        return passengerFirstName
    }

    String getPassengerLastName() {
        return passengerLastName
    }

}
