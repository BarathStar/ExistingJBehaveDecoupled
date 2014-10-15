Adult Air Checkin from Home Page Account Bar

Meta:
@flow air
@process checkin
@user anonymous
@traveler adult
@dyna_stubs

Narrative:
In order to receive my boarding pass
As an adult
I want to checkin for a flight from the account bar


Scenario: Adult Checkin

Given I search for a roundtrip air ticket from DAL to HOU
When I book a flight eligible for checkin 1 adult
And I am on the Home page
And I click on the Check In Section of the Account Bar or Check In Tab on Home Page
And I enter the confirmation number, first name, last name, and click check in
And I click checkin to create a boarding pass
And I click on the Continue button if I am in the MBP options page
Then I view my boarding pass