Search stations on My Rapid Rewards Tab book a trip modal

Meta:
@flow air
@process booking
@user rr_member
@traveler adult
@project webreferral
@dyna_stubs

Narrative:
In order to verify stations on  My Rapid Rewards Tab book a trip modal
As a Rapid Rewards Member
I want to search for trips using My Rapid Rewards Tab book a trip modal

Scenario: Rapid Rewards Travel Preferences
Given I am logged in as Rapid Rewards member on the Rapid Rewards Account page
When I select My Rapid Rewards link
And I select book a flight
And I select Atlanta in the From field
And I select CUN in the To field
And I click on the modal search button
Then view the AirTran Redirect Modal with WCM Content
