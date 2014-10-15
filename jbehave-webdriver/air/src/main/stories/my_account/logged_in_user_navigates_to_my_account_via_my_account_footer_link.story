A Southwest user currently logged in navigates to the My Account page using the My Account link in the page footer

Meta:
@flow other
@process view
@dyna_stubs
@story_id BUG-69

Narrative:
As a RR User who is currently logged in,
I want to view My Account by clicking on the My Account in the footer on the Home Page.
Since I am already logged in, I should be taken directly to the My Account page.

Scenario: A Southwest User (currently logged in) clicks on the My Account link in the page footer and is taken directly to the My Account page.

Given I am a Rapid Rewards Member
And I am logged in and on the home page
When I click on the My Account link in the footer
Then I am taken to the My Account page
