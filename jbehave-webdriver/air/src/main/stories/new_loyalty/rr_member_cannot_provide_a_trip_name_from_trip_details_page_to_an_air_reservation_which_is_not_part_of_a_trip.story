Check that it cannot be renamed as it is not part of a trip.

Meta:
@flow rr
@process loyalty
@user rr_member
@not_live
@dyna_stubs

Narrative:
In order to validate that a trip name cannot be provided to an Air reservation
As a Rapid Rewards Member (with Upcoming Trips)
I want to retrieve my Air reservation from Trip Details page and check there is no option to change the default name

Scenario: Rapid Rewards Member cannot provide a trip name from "Trip Details" page to an Air reservation which is not part of a trip
Given I am a Rapid Rewards Member passenger
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

And I have at least one Upcoming Trip in my account with an Air product which is not part of a trip
When I login from Login page
And I click my account link
And I decide to view all the upcoming trips from account's Snapshot
And I select an Air reservation which is not part of a trip from Upcoming Trips page
Then I should not be able to rename the Air reservation on the Trip Details page
And I see on the Trip Details page that the Air reservation has no trip name provided by the user
And I see the details of the Air reservation on the Trip Details page