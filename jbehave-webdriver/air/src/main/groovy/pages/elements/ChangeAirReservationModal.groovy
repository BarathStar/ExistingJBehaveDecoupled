/* Copyright (c) 2011, Southwest Airlines Co.  All rights reserved. */
package pages.elements

import org.openqa.selenium.By
import org.openqa.selenium.WebElement

/**
 * This represent a Change Air Reservation modal
 */
class ChangeAirReservationModal extends Modal{

    private static final By ASSOCIATED_PRODUCTS_TITLE = By.className("cancellation_modal_associated_products_title")
    private static final By AIR_PRODUCT = By.className("air_itinerary_container_with_image")
    private static final By AIR_FLIGHT_ITINERARY = By.id("flightItinerary")
    private static final By AIR_FLIGHT_DATES = By.id("flightDates")
    private static final By CONFIRMATION_NUMBER = By.className("confirmation_number")
    private static final By HOTEL_PRODUCT = By.className("hotel_itinerary_container_with_image")
    private static final By HOTEL_NAME = By.className("hotel_itinerary_hotel_name")
    private static final By HOTEL_CHECKIN_CHECKOUT_DATES = By.className("hotel_itinerary_hotel_date")
    public static final By CAR_PRODUCT = By.className("car_itinerary_container_with_image")

    ChangeAirReservationModal(WebElement theContainer){
        super(theContainer)
    }

    WebElement getAirProduct() {
        container.findElement(AIR_PRODUCT)
    }

    WebElement getHotelProduct() {
        container.findElement(HOTEL_PRODUCT)
    }

    WebElement getCarProduct() {
        container.findElement(CAR_PRODUCT)
    }

    String getAssociatedProductsTitle(){
        container.findElement(ASSOCIATED_PRODUCTS_TITLE).getText()
    }

    String getAirFlightItinerary() {
        getAirProduct().findElement(AIR_FLIGHT_ITINERARY).getText()
    }

    String getAirFlightDates() {
        getAirProduct().findElement(AIR_FLIGHT_DATES).getText()
    }

    String getAirConfirmationNumber() {
        getAirProduct().findElement(CONFIRMATION_NUMBER).getText()
    }

    String getHotelName() {
        getHotelProduct().findElement(HOTEL_NAME).getText()
    }

    String getHotelDates() {
        getHotelProduct().findElement(HOTEL_CHECKIN_CHECKOUT_DATES).getText()
    }

    String getHotelConfirmationNumber() {
        getHotelProduct().findElement(CONFIRMATION_NUMBER).getText()
    }
}
