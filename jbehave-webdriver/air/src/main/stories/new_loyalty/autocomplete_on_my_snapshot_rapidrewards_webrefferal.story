Search stations on my snapshot book a trip modal

Meta:
@flow rr
@process loyalty
@user rr_member
@traveler adult
@project webreferral

Narrative:
In order to verify stations on my snapshot book a trip modal
As a Rapid Rewards Member
I want to search for trips using my snapshot book a trip modal

Scenario: Rapid Rewards Travel Preferences

Given I am logged in as Rapid Rewards member on the Rapid Rewards Account page
When I click the book a trip button
When I select ATL in the From field
And I select CUN in the To field
Then I should see Atlanta, GA - ATL in From field
And I should see Cancun, MX - CUN in To field
When To,From fields are empty
And I select ATL in the From field
Then I should see Atlanta, GA - ATL in From field
When From field is empty
When I select CUN in the From field
Then I should see Cancun, MX - CUN in From field
