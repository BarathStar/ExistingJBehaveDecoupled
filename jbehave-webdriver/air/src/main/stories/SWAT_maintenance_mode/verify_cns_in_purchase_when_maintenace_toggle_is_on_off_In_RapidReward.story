verify that Click And Save is allow or not in purchase depend on the RREdit maintenance toggle status when logged in with Rapid Reward account

Meta:
@flow air
@process booking
@user rr
@passenger adult
@project SWAT_Maintenance
@not_live
@dyna_stubs
@project_in_dev

Narrative:
In order to fly on a date that I want
As an adult
I want to see if the Click And Save option is available

Scenario: verify that Click And Save is not allowed in purchase with RREdit maintenance toggle status when logged in with Rapid Reward account

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
When I am on the purchase page
Then I should not see Click And Save option