Search stations on my snapshot book a trip modal

Meta:
@suite regression
@flow air
@process booking
@storyId DCAIR6730
@project webreferral
@not_passing broken
@message 2012-02-08 - Don Walker - Siebel Tier Processing temporarily disrupting testing.

Narrative:
In order to verify stations on my snapshot book a trip modal
As a Rapid Rewards Member
I want to search for trips using my snapshot book a trip modal

Scenario: Rapid Rewards Travel Preferences

Given I am logged in as Rapid Rewards member on the Rapid Rewards Account page
When I click the snapshot link
And I select book a flight
And I select Atlanta in the From field
And I select CUN in the To field
And I click on the modal search button
Then view the AirTran Redirect Modal with WCM Content
