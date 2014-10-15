view previously created promo-cert

Meta:
@storyId MH-704

Narrative:
As a customer logged into R.R account with promo-certs
I want to view my promo-certs
So that I can use them

Scenario: Log in as Rapid Rewards user, verify presence of existing promo-certs

Given I am logged in as a promoCertUser Rapid Rewards member on the Search Flights page
When I click my account link
And I select My Rapid Rewards link
Then I see that I have promo certs