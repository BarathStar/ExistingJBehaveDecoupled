Block an attempt to view an air itinerary with a PNR from MyIdTravel when user select the credit card option

Meta:
@flow air
@process view
@user anonymous
@traveler adult
@dyna_stubs
@not_live
@project my_id_travel

Narrative:
In order to receive an Oops message for a MyID Travel PNR
As an adult
I want to view my air itinerary an receive an Oops message

Scenario: Block MyIDTravel adult air view when user select the credit card option

Given I am flying a round-trip Southwest Southwest flight
And I have a MyIdTravel flight booked with Credit Card
When I retrieve my air itinerary by Credit Card
Then I see the My Id Travel Oops message on view itinerary page