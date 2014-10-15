Verify Seat Selection Button Not Displayed on the Check-In Print Boarding Documents Page

Meta:
@project cr1
@airTranOnly
@flow air
@process changing
@traveler adult
@storyId ZR-1151
@seatSelectionOff
@dyna_stubs
@project_in_dev

Narrative:
In order to verify the AirTran Seat Selection Button on the Check-In Print Boarding Documents Page is not displayed
As a customer
I want to Check-In online for an existing AirTran iteinerary
So that I do not see the AirTran Seat Selection Button on the Check-In Print Boarding Documents Page

Scenario: Verify AirTran Seat Selection Button on Check-In Print Boarding Documents Page

Given I have an AirTran reservation that is eligible for check in
When I check in from the check in page
Then I DO NOT see the AirTran Seat Selection Button on the Checkin and Print Boarding Documents Page