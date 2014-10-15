Validate that the Swabiz company name is displayed in Pricing page when user is anonymous logged on Swabiz and book an air product

Meta:
@flow air
@process booking
@user anonymous
@traveler adult
@removedFromAirbooking
@dyna_stubs


Narrative:
As an adult
I want to verify the Swabiz company name on the Swabiz pricing page
So that I can see the info correctly

Scenario: Initial Itinerary - Initial booking

Given I am on the swabiz home page
And I have anonymously logged into a SWABiz account
And I am traveling as a:
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

And I search for a flight
When I select and view the Price page for a flight
Then I should see Swabiz company information displayed on price page of swabiz