An anonymous user (not currently logged in) navigates to the My Account page using the Travel Tools | Book Hotel | Log Into Southwest link

Meta:
@flow other
@process view
@dyna_stubs
@story_id BUG-69

Narrative:
As an anonymous user,
I want to view My Account by clicking on the Book Hotel | Log Into Southwest link on the Travel Tools page.
I should be taken to the My Account Login page, and after successfully logging in, I should be taken to the My Account page.

Scenario: An anonymous user (not currently logged in) clicks on the Travel Tools | Book Hotel | Log Into Southwest link and is taken to the My Account Login page, where they must first log in. After successfully logging in, they are taken to the My Account page.

Given I am a Rapid Rewards Member
And I am on the Travel Tools page
When I click on the Book Hotel Log into MySouthwest link
Then I am taken to the Account Login page
And I log in on the Account Login page
And I am taken to the My Account page
