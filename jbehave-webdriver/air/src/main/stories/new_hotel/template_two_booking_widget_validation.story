Verify that validations are being done within the bottom hotel booking widget before it is submitted. (Template 2)

Meta:
@flow hotel
@process booking
@user anonymous
@traveler adult
@dyna_stubs

Narrative:
As a user
I want to verify that oops messages are being shown to the user
In order to validate bottom hotel booking widget on the Hotel Promotion Template 2

Scenario: Bottom hotel booking widget oops message validation on the Hotel Promotions Template 2
Given I am on Hotel Promotions Template 2
When I attempt to book using the bottom hotel booking widget without completing any field
Then I should see an oops message stating that I should add data