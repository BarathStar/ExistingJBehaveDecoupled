Verification that the email confirmation field does not get wiped out when attempting to apply travel funds

Meta:
@flow air
@process booking
@user anonymous
@traveler adult
@dyna_stubs

Narrative:
As a User
I want my email confirmation address to be saved when trying to apply travel funds

Scenario: Apply Travel Funds during purchase flow
Given I am flying a one-way Southwest flight
When I search and select flights and continue to the Purchase page
And I fill out the purchase form
And I click on Apply Travel Funds
Then The Email Confirmation Address field should not be empty