SWABIZ - BUG-2558(b) - Senior Passenger Count and Promo Code options are (re)enabled when booking Air and selecting Show Fares in Dollars.

Meta:
@flow air
@process booking
@user anonymous
@traveler adult
@dyna_stubs
@not_live

Narrative:
On the Book Air page, clicking on Show Fares in Dollars should (re)enable the Senior Passenger Count and Promo Code options.

Scenario: On the Book Air page, switch from Show Fares in Dollars to Points (disabling the Senior Passenger Count and Promo Code options). Then switch back to Dollars, verifying that the Senior Passenger Count and Promo Code options are (re)enabled.

Given I have the following itinerary data:
    |Field|Value|
    |itineraryType|One Way|
    |departureStation|DAL|
    |arrivalStation|HOU|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Anytime|
And I am a SWABIZ Traveler located in the Traveler Account Login
And I have my RR account setup with an associated company ID
And I login into SWABIZ by entering my company ID, my account number and password
And I select the Book Travel tab
And I select the Show Fares in Points option
When I select the Show Fares in Dollars option
Then the Senior Passenger Count option is enabled
And the Promo Code option is enabled