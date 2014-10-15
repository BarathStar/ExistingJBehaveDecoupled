Redirect to southwest select flight page from my account travel link

Meta:
@suite regression
@flow air
@process booking
@storyId DCAIR6730
@project webreferral
@not_passing broken
@message 2012-02-08 - Don Walker - Siebel Tier Processing temporarily disrupting testing.

Narrative:
In order to verify redirection to southwest select flight page from my account
As a Rapid Rewards Member
I want to search for trips using my travel book a trip modal

Scenario: Rapid Rewards my Travel link

Given I am logged in as Rapid Rewards member on the Rapid Rewards Account page
When I select My Travel link
And I select book a flight
And I select LAX in the From field
And I select BWI in the To field
And I click on the modal search button
Then I should see the southwest select flight page
