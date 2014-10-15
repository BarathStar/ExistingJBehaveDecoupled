Validate that the Swabiz company name is not displayed in Pricing page when an anonymous user is logged on Swabiz but book an air on Dotcom

Meta:
@flow air
@process booking
@user anonymous
@traveler adult
@dyna_stubs

Narrative:
As an adult
I want to verify that the Swabiz company name is not displayed on the pricing page of southwest.com but logged in Swabiz
So that I can see the info correctly

Scenario: Initial Itinerary - Initial booking

Given I have anonymously logged into a SWABiz account
And I am:
    |Field|Value|
    |adultPassengerCount|1|
    |seniorPassengerCount|0|

And I have the following itinerary data:
    |Field|Value|
    |itineraryType|One Way|
    |departureStation|DAL|
    |arrivalStation|HOU|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Anytime|
    |outboundRouting|Nonstop|
    |departureDate|+7|
    |adultPassengerCount|1|

And I am on the select flights page
When I select and view the Price page for a flight
Then I should not see Swabiz company information displayed on price page of .com