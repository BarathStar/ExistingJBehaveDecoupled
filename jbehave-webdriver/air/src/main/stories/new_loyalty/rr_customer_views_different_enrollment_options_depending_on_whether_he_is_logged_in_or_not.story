Enroll in  the Rapid Rewards program.

Meta:
@flow rr
@process loyalty
@dyna_stubs

Narrative:
In order to see the different enrollment options to the Rapid Rewards program
As a Rapid Rewards Customer
I want to check the account sidebar when I am not logged in and when I am logged in

Scenario: Rapid Rewards Customer (not Member) views different enrollment options depending on whether he is logged in or not
Given I am a Customer not logged in
When I access Search Flight page
Then I see Enroll Now button on the account sidebar under My Rapid Rewards section not logged in
When I log in as a customer from the account sidebar at the Search Flight page
Then I see Enroll Now button on the account sidebar under My Rapid Rewards section logged in