Attempt to add a Rapid Reward number to a group PNR

Meta:
@dyna_stubs
@flow air
@not_live
@passenger adult
@process booking
@user rr_member
@removedFromAirbooking

Narrative:
As an adult
In order to add RR account to a group PNR
I attempt to retrieve a RR number to a group PNR

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

And I have booked this group reservation with all its passengers eligible for checkin online from QIK
And I am a Rapid Rewards Member
And I am on the Add Rapid Rewards Number Page
When I enter my PNR on the add Rapid Rewards number page
Then I should be on the Enter Rapid Rewards number page
When I enter my Rapid Rewards Number on the add Rapid Rewards Number page
Then I see my Rapid Rewards Number added to my itinerary