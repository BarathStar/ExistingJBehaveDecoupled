Redirect to Air tran from my account travel link

Meta:
@flow air
@process booking
@user rr_member
@traveler adult
@removedFromAirbooking
@dyna_stubs
@project webreferral

Narrative:
In order to verify redirection to Air tran from my account
As a Rapid Rewards Member
I want to search for trips using my travel book a trip modal

Scenario: Rapid Rewards my Travel link
Given I am logged in as Rapid Rewards member on the Rapid Rewards Account page
When I select My Travel link
And I select book a flight
And I select Atlanta in the From field
And I select CUN in the To field
And I click on the modal search button
Then view the AirTran Redirect Modal with WCM Content
When I click to continue from the AirTran Redirect Modal
Then I should see the AirTran search results page