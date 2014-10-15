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
I want to purchase EarlyBird check-in more than 36 hours prior
So that I can board early

Scenario: Allow EarlyBird purchase more than 36h before flight is allowed

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

And Only the following one way schedule is available from GEG to PVD on +36h30m:
    |ond    |flight  |origin |departure   |destination|arrival|carrier|
    |1      |1001    |GEG    |+36h30m     |PVD        |+5h30m |WN     |

And The following routes are available:
    |Field|Value|
    |originStation|GEG|
    |destinationStation|PVD|
    |carrierDates|WN:T:[today+0,today+15]|

And I want to fly from from GEG to PVD on day of +36h30m

When I attempt to search for flights from the flight search page
And I select outbound flight number 1001
And I continue from the select flights page to the purchase page
And I fill out the purchase form with Early Bird
Then I should be able to purchase EB
When I continue to Confirmation page from Purchase page
Then I should see the confirmation page
And Early Bird is present on the confirmation page
