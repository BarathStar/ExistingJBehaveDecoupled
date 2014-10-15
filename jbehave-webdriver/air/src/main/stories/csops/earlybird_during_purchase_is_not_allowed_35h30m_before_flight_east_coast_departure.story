Meta:
@flow air
@process purchase
@user anonymous
@traveler adult
@dyna_stubs
@not_live
@not_passing
@diagnosing

Narrative:
As an anonymous user
I do not want to see the early bird purchase option when it is not available
So that I will only be able to select it when I can successfully purchase it

Scenario: Do not allow EarlyBird purchase less than 36h before flight is allowed from east coast

Given I have the following itinerary data:
    |Field|Value|
    |itineraryType|One Way|
    |departingFlight_fareClass|Anytime|
    |departingFlight_carrierCode|WN|
    |departingFlight_airlineName|Southwest Airlines|

And I am traveling as a:
    |Field|Value|
    |adultPassengerCount|1|
    |seniorPassengerCount|0|

And Only the following one way schedule is available from PVD to SEA on +35h30m:
    |ond    |flight  |origin |departure   |destination|arrival|carrier|
    |1      |1001    |PVD    |+35h30m     |SEA        |+5h30m |WN     |

And The following routes are available:
    |Field|Value|
    |originStation|PVD|
    |destinationStation|SEA|
    |carrierDates|WN:T:[today+0,today+15]|

And I want to fly from from PVD to SEA on day of +35h30m

When I attempt to search for flights from the flight search page
And I select outbound flight number 1001
And I continue from the select flights page to the purchase page
Then I should not be able to purchase EB
