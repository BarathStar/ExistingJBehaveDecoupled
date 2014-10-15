Get verified when a minor is traveling without companion so that I know that the passenger of an air reservation is an unaccompanied minor after retrieving the reservation

Meta:
@flow air
@process view
@user anonymous
@traveler um
@dyna_stubs
@project UM/YT

Narrative:
In order to know that a Minor (pax age: 5-11) is traveling alone
As an anonymous user
I want to be informed that a passenger is a minor who travels without companion while retrieving an air reservation

Scenario: Customer is informed that the passenger is an unaccompanied minor after retrieving an air reservation
Given I have booked a flight for an Unaccompanied Minor
When I retrieve my itinerary
Then I see an icon which informs that the passenger is an Unaccompanied Minor on the View Reservation Page
And I see an informative text which indicates that the passenger is an Unaccompanied Minor on the View Reservation Page
