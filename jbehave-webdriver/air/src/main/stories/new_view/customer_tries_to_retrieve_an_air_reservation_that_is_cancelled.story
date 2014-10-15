Retrieve an Air reservation that is cancelled

Meta:
@flow air
@process view
@user anonymous
@traveler adult
@dyna_stubs
@not_passing broken
@project tmAir

Narrative:
In order to retrieve an Air product which belongs to a trip
As a User
I want to be shown an error message when the Air reservation has been cancelled

Scenario: Customer attempts to retrieve an Air reservation which is cancelled
Given I have cancelled a booked trip named My Trip
When I retrieve my itinerary
Then I view an Oops message which indicates that my reservation has been cancelled