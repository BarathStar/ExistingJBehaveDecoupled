Enter stations using auto-complete on the Marketing Page. Verify AirTran stations and market pairs


Meta:
@flow air
@process booking
@user anonymous
@traveler adult
@project webreferral

Narrative:
In order to enter stations using auto-complete on the Marketing Page
As an adult
I want to see auto-complete functionality on Marketing page
So that I can find stations easily

Scenario: Auto-complete on From & To field
Given I am on the Marketingpage
When I enter ATL in the From field
Then I should see the auto complete dropdown for airtran ATL from Marketingpage
When I enter CUN in the To field
Then I should see the auto complete dropdown for airtran CUN from Marketingpage
When To,From fields are empty
And I enter ATL in the From field
Then I should see the auto complete dropdown for airtran ATL from Marketingpage
When From field is empty
When I enter CUN in the From field
Then I should see the auto complete dropdown for airtran CUN from Marketingpage
When From field is empty
When I enter ATL in the To field
Then I should see the auto complete dropdown for airtran ATL from Marketingpage
When To field is empty
When I enter CUN in the To field
Then I should see the auto complete dropdown for airtran CUN from Marketingpage
