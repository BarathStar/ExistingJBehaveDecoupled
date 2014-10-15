Verify oops message showed trying to view travel funds unused on Travel Funds Page

Meta:
@flow air
@process view
@user anonymous
@passenger adult
@dyna_stubs
@not_live

Narrative:
As an user
I want to see an special oops message showed trying to use travel funds used in a flight that has not been departed
So that

Scenario: Verify oops message showed trying to view travel funds unused

Given I have a valid Travel Fund
And I am on the Home page
When I go to the View Reservation page through the Air menu
And I click on the View Travel Funds link
And I fill in travel fund's with invalid PNR NOT_FOUND
And I confirm to Check the Travel Funds
Then I view the OOPS message for the invalid PNR checking travel funds