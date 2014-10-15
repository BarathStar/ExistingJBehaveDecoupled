Purchase a round trip ticket for an adult and verify that the credit card information is not saved from a previous purchase

Meta:
@flow air
@process booking
@user anonymous
@passenger adult
@dyna_stubs
@storyId SWAT-1630
@project SWAT
@project_in_dev

Narrative:
As an Adult
In order to do a purchase
I want the system do not remember my credit card information
So that

Scenario: Verify that credit card information is not saved in a previous purchase

Given I am flying a round-trip Southwest Southwest flight
And I have booked a flight
When I am on the purchase page
Then I should not see the credit card holder info