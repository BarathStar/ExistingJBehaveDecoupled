Adult Air Check Flight from Home Page Account Bar

Meta:
@flow air
@process flightStatus
@user anonymous
@traveler adult
@dyna_stubs

Narrative:
In order to check my flight
As an adult
I want to check flight status from the account bar


Scenario: Adult Check Flight Status

Given I am on the Home page
When I click on the Check Flight Status Section of the Account Bar or Flight Status Tab on Home Page
And I enter the origin, destination, date, and click continue
Then I verify I am on the Flight Status Page