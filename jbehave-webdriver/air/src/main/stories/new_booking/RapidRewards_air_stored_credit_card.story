Purchase A Rapid Rewards Air Ticket in Dollars

Meta:
@flow air
@process booking
@user rr_member
@traveler adult
@dyna_stubs
@project_AccordionPage

Narrative:
In order to fly on a date that I want
As a Rapid Rewards Member
I want to book a flight using Dollars and
receive a confirmation page with RR number displayed

Scenario: Rapid Rewards Member air purchase
Given I am traveling as a:
    |Field|Value|
    |adultPassengerCount|1|
    |seniorPassengerCount|0|

And I have the following itinerary data:
    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|HOU|
    |arrivalStation|DAL|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Anytime|
    |outboundRouting|Nonstop|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|Anytime|
    |inboundRouting|Nonstop|

And I am logged in as a valid Rapid Rewards member on the Search Flights page
When I search and book a flight from search flights page
Then I should see the confirmation page that has RR number displayed
