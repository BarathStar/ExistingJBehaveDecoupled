Unaccompanied Minors are not eligible for check-in online

Meta:
@flow air
@process checkin
@user anonymous
@traveler um
@dyna_stubs
@project UM/YT

Narrative:
In order to validate that Unaccompanied Minors are not eligible for check-in online
As an anonymous user
I want to be shown an error message when I attempt to check-in online for and Unaccompanied Minor (pax age: 5-11) flight

Scenario: UM are not eligible for check-in online
Given I have booked a flight eligible for check-in for an Unaccompanied Minor
When I retrieve my reservation to checkin
Then I view an Oops message which indicates that Unaccompanied Minors are not eligible for check-in online