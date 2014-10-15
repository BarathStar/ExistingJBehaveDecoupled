Attempt a group checkin for ineligible passengers

Meta:
@flow air
@process checkin
@user anonymous
@traveler adult
@project group_checkin
@dyna_stubs
@not_live

Narrative:
In order to perform the group check-in for all passengers
As an adult travelling with 9 other adults
I want to attempt to complete the check-in for a group of passengers

Scenario: View error message when trying to check-in a Group Reservation with ineligible passengers
Given I am:
    |Field|Value|
    |adultCheckInPassengerCount|10|
    |seniorPassengerCount|0|

And I have the following itinerary data:
    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|HOU|
    |departureDate|+1|
    |arrivalStation|DAL|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Anytime|
    |outboundRouting|Nonstop|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|Anytime|
    |inboundRouting|Nonstop|

And I have booked this group reservation eligible to checkin online
And I have my reservation with all its passengers ineligible for checkin online
When I retrieve my reservation to checkin
Then I see a message which informs me that the passenger(s) require airport checkin for the outbound and inbound flight
