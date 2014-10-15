A Southwest user (already logged in) navigates to the My Account page using the Travel Tools | Book Air | Log Into Southwest link

Meta:
@flow other
@process view
@dyna_stubs
@story_id BUG-69

Narrative:
As a logged in user,
I want to view My Account by clicking on the Book Air | Log Into Southwest link on the Travel Tools page.
I should be taken directly to the My Account page, since I am already logged in.

Scenario: An anonymous user (currently logged in) clicks on the Travel Tools | Book Air | Log Into Southwest link and is taken directly to the My Account page.

Given I am a Rapid Rewards Member
And I am on the Travel Tools page
When I click on the Book Air Log into MySouthwest link
Then I am taken to the My Account page
