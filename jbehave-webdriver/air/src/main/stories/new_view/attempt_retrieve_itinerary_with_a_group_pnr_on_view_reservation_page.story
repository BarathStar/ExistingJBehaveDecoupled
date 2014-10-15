Attempt to retrieve an Itinerary using a group PNR

Meta:
@flow air
@process view
@user anonymous
@traveler adult
@project view_all_pnrs
@dyna_stubs
@not_live

Narrative:
In order to see the OOPS message
As an anonymous user
I attempt to retrieve an Itinerary using a group PNR on the View/Share Itinerary page

Scenario: Attempt to retrieve an itinerary using a group PNR

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

And I have booked this group reservation
And I am on the view/share itinerary landing page
When I retrieve my itinerary
Then I view the OOPS message for the invalid PNR on the View/Share Itinerary page