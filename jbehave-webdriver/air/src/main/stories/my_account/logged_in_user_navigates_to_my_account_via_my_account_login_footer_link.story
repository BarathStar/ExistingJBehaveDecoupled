A Southewst User (currently loggedin) navigates to the My Account page using the My Account | Login link in the page footer

Meta:
@flow other
@process view
@dyna_stubs
@story_id BUG-69

Narrative:
As a RR User who is currently logged in,
I want to view My Account by clicking on the My Account | Login link in the footer on the Home Page.
I should be taken directly to the My Account page, and should not be required to login (since I am already logged in).

Scenario: A RR User (currently logged in) clicks on the My Account | Login link in the page footer and is taken directly to the My Account page.

Given I am a Rapid Rewards Member
And I am logged in and on the home page
When I click on the My Account link in the footer
Then I am taken to the My Account page
