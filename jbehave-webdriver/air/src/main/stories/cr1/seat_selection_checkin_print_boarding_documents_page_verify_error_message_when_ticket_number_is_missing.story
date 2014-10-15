Verify Seat Selection Button shows an error message on the Check-In Print Boarding Documents Page when the navitaire ticket number is missing

Meta:
@project cr1
@airTranOnly
@flow air
@process changing
@traveler adult
@storyId ZR-1658
@seatSelection
@dyna_stubs
@project_in_dev

Narrative:
In order to verify the AirTran Seat Selection Button shows an error message on the Check-In Print Boarding Documents Page
As a customer
I want to Check-In online for an existing AirTran itinerary that doesn't have a navitaire assigned ticket number
So that I can see the AirTran Seat Selection Button shows an error message on the Check-In Print Boarding Documents Page

Scenario: Verify AirTran Seat Selection Button shows an error message on Check-In Print Boarding Documents Page

Given I have an AirTran reservation that is eligible for check in and is missing ticket numbers
When I check in from the check in page
And I click the seat selection button on the checkin page
Then I see the seat selection unavailable message for the AirTran flight on the checkin page
