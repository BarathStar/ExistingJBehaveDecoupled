Verify an Oops message appear for attempting to buy an EarlyBird in .com for a booking purchased in swabiz as an adult


Meta:
@flow air
@process checkin
@user anonymous
@passenger adult
@dyna_stubs



Narrative:
As an adult
In orden to buy an Early Bird checking from .com
I want to see an Oops error message appear when the booking was made from swabiz
So that

Scenario: Verify an Oops error message appear when you try to buy an Early Bird from .com

Given I am on the swabiz home page
And I have anonymously logged into a SWABiz account
And I am traveling as a:

    |Field|Value|
    |adultPassengerCount|1|
    |seniorPassengerCount|0|

And I have the following itinerary data:

    |Field|Value|
    |itineraryType|One Way|
    |departureStation|SFO|
    |outboundConnectingStation|DEN|
    |arrivalStation|BWI|
    |departingFlight_carrierCode|WN|
    |outbound_connection_carrierCode|WN|
    |departingFlight_fareClass|Anytime|
    |outboundRouting|Change Planes|

When I have a booked SwaBiz flight
When I am on the early bird page
And I try to retrieve itinerary on the Early Bird check-in page
Then I view the OOPS message for attempting to buy a EarlyBird in .com for a booking purchased in swabiz



