Verify that an Oops error page appear when you are trying to save flight information on price page due you had forced a new session opening in a new window the check-in page

Meta:
@flow air
@process view
@user rr
@passenger adult
@dyna_stubs
@not_live

Narrative:
In order to do a booking
As an adult Rapid Reward Adult
I want to see an Oops error page saying that is not possible save flight information at the moment

Scenario: verify that an Oops error page appear when you are trying to save flight information on price page due you had forced a new session opening in a new window the check-in page

Given I am traveling as a:
|Field|Value|
|adultPassengerCount|1|
|seniorPassengerCount|0|

And I have the following itinerary data:
|Field|Value|
|itineraryType|Round Trip|
|departureStation|ANY|
|arrivalStation|ANY|
|departingFlight_carrierCode|WN|
|departingFlight_fareClass|Anytime|
|outboundRouting|Nonstop|
|arrivingFlight_carrierCode|WN|
|arrivingFlight_fareClass|Anytime|
|inboundRouting|Nonstop|

And I am logged in as Rapid Rewards member on the Rapid Rewards Account page
When I search for flights from my account
And I select flights and continue
And I open check-in page in another window
And I come back to previous page in another windows
And I click the Save Flight button
Then I should see an Oops saying that is not possible save flight at the moment