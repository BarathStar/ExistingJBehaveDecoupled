Add rr Number to a multi pax reservation

Meta:
@flow air
@process booking
@user rr_member
@traveler adult
@not_live
@dyna_stubs

Narrative:
When I add RR number to a multi-pax reservation
As a Rapid Rewards Member
I want to be able to add RR Number to a single passenger and leave another passenger blank

Scenario: Rapid Rewards Member attempts to change an Air reservation accessing through "Change" link on "Trip Details" page
Given I am a Rapid Rewards Member passenger
And I have 2 more passengers traveling with me
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

And I have included an Air in my shopping cart
And I have finished my purchase adding my products to a new trip named My Trip
Given I am on the Add Rapid Rewards Number Page
When I enter my PNR on the add Rapid Rewards number page
Then I should be on the Enter Rapid Rewards number page
When I enter my Rapid Rewards Number 22 on the add Rapid Rewards Number page at Passenger 2
Then I see my Rapid Rewards Number added to my itinerary