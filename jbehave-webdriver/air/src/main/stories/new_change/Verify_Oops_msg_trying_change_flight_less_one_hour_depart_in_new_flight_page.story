Verify Oops message when attempt to change a flight that depart in less than an hour when select a new flight as an user

Meta:
@flow air
@process change
@user anonymous
@passenger adult
@dyna_stubs
@not_live

Narrative:
As an adult
I should see an Oops message on New Flight Page when I attempt to change a flight that departs less than an hour
So that

Scenario: Verify Oops message when I attempt to change a flight that departs in less than an hour on new search flight page

Given I have the following itinerary data:
    |Field|Value|
    |itineraryType|One Way|
    |departureStation|MSP|
    |departureDate|+2|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Anytime|
    |arrivalStation|LAS|
    |outboundRouting|Nonstop|

And I have a flight booked
And Only the following one way schedule is available from MSP to LAS on +0d:
    |ond    |flight|origin |departure  |destination|arrival|carrier|
    |1      |10    |MSP    |+0d1h4m    |LAS        |+3h3m  |WN     |

And The following routes are available:
    |Field|Value|
    |originStation|MSP|
    |destinationStation|LAS|
    |carrierDates|WN:T:[today,today+2]|
When I retrieve the Air reservation to change it on the Change Air Reservation Page
And I select to change my entire itinerary and continue
And I click continue on the Change Air Reservation Modal
And I change the departure date as of today
And I click Continue to the New Select Flight Page
And I select outbound flight number 10
And I wait for 100 seconds to so my flight have less than an hour for the departure time
And I click continue on Select Flight page
Then I see Oops message that says:
 |message|
 |You are trying to book a flight that departs in less than one hour. Please select another flight.|