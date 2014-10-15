An anonymous user (not currently logged in) navigates to the My Account page using the My Account link in the page footer

Meta:
@flow other
@process view
@dyna_stubs
@story_id BUG-69

Narrative:
As an anonymous user,
I want to view My Account by clicking on the My Account link in the footer on the Home Page.
I should be taken to the My Account Login page, and after successfully logging in, I should be taken to the My Account page.

Scenario: An anonymous user (not currently logged in) clicks on the My Account link in the page footer and is taken to the My Account Login page, where they must first log in. After successfully logging in, they are taken to the My Account page.

Given I am a Rapid Rewards Member
And I am on the Home page
When I click on the My Account link in the footer
Then I am taken to the Account Login page
And I log in on the Account Login page
And I am taken to the My Account page
