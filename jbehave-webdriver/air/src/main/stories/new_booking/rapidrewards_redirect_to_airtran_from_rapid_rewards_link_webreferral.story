Redirect to southwest select flight page from my rapid reward link

Meta:
@flow air
@process booking
@user anonymous
@traveler adult
@removedFromAirbooking
@dyna_stubs
@project webreferral

Narrative:
In order to verify redirection to airtran page
As a Rapid Rewards Member
I want to search for trips using my rapid rewards book a trip modal

Scenario: My account rapid reward link
Given I am logged in as Rapid Rewards member on the Rapid Rewards Account page
When I select My Rapid Rewards link
And I select book a flight
And I select Atlanta in the From field
And I select CUN in the To field
And I click on the modal search button
Then view the AirTran Redirect Modal with WCM Content
When I click to continue from the AirTran Redirect Modal
Then I should see the AirTran search results page