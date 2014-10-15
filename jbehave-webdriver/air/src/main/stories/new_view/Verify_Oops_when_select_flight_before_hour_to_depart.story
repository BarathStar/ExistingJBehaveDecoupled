Verify Oops message when I attempt to book a flight that departs in less than an hour selecting a flight as an user

Meta:
@flow air
@process view
@user anonymous
@passenger adult
@dyna_stubs
@not_live

Narrative:
As an adult
I should see an Oops message when I attempt to book a flight that departs less than an hour
So that

Scenario: Verify Oops message when I attempt to book a flight that departs in less than an hour on Select Flight page

Given I am traveling as a:
    |Field|Value|
    |adultPassengerCount|1|
    |seniorPassengerCount|0|

And Only the following one way schedule is available from HOU to LAX on +0d:
    |ond    |flight|origin |departure  |destination|arrival|carrier|
    |1      |14    |HOU    |+0d1h3m    |LAX        |+3h3m  |WN     |

And The following routes are available:
    |Field|Value|
    |originStation|HOU|
    |destinationStation|LAX|
    |carrierDates|WN:T:[today,today+2]|

And I have the following itinerary data:
    |Field|Value|
    |itineraryType|One Way|
    |departureStation|HOU|
    |departureDate|+0|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Anytime|
    |arrivalStation|LAX|
    |outboundRouting|Nonstop|

When I attempt to search for flights from the flight search page
And I select outbound flight number 14
And I wait for 155 seconds to so my flight have less than an hour for the departure time
And I click continue on Select Flight page
Then I see Oops message that says:
 |message|
 |You are trying to book a flight that departs in less than one hour. Please select another flight.|