Verify that user selecting optional travel charges in the page header will go to the correct URL.

Meta:
@flow air
@traveler adult
@process view
@user anonymous
@dyna_stubs

Narrative:
As a southwest.com user
I want to be able to access the Optional Travel Charges page
So that I can read information on SWA fees

Scenario: Any visitor to southwest.com selects Optional Travel Charges link on the homepage under the account bar

Given I am a Southwest user at the homepage
When I select Optional Travel Charges beneath the account bar
Then I see the Optional Travel Charges page
