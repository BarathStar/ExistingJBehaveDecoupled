Do not prevent an Unaccompanied Minor from Purchasing an Air Ticket on AirTran

Meta:
@project cr1
@airTranOnly
@flow air
@process booking
@user anonymous
@storyId DCQA28
@traveler minor senior
@data schedule


Narrative:
In order to fly on a date that I want
As an unaccompanied minor with a senior passenger
I can book a flight for travel on AirTran


Scenario: Minor (child 5-11) allowed to book with a senior passenger accompanying on a SW-FL round-trip flight air purchase

Given I am flying a round-trip SouthwestCodeshare AirTran flight
When I book a flight for senior and child passengers
Then I receive an air confirmation number

