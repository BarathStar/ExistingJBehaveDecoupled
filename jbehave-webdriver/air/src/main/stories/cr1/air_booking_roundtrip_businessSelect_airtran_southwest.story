Verify AirTran\Southwest Business Select itinerary displayed on Confirmation Page

Meta:
@project cr1
@airTranOnly
@flow air
@process booking
@user anonymous
@traveler adult
@storyId DCQA24 ZR-979
@dyna_stubs
@project_in_dev


Narrative:
In order to book a flight using Business Select fare type on AirTran and SouthWest
As a customer
I want to verify AirTran\SouthWest itinerary displayed on confirmation page


Scenario: Viewing a FL_WN Business Select Itinerary

Given I am flying a BusinessSelect round-trip FLWNCodeShare FLWNCodeShare flight
When I have a flight booked
And I retrieve my itinerary
Then I see the business select text
