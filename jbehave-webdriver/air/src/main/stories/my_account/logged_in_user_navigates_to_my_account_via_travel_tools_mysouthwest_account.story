A Southwest user currently logged in navigates to the My Account page using the Travel Tools | MySouthwest Account dropdown link

Meta:
@flow other
@process view
@dyna_stubs
@story_id BUG-69
@not_passing draft

!-- TODO: When you click on the Travel Tools dropdown, and then attempt to click MySouthwest Account, Selenium throws an exception,
!-- "MoveTargetOutOfBoundsException: Element cannot be scrolled into view" for some inexplicable reason. Google research of the
!-- problem reveals plenty of anecdotal evidence that others have encountered the same issue (see Selenium Issue 3075), which seems to have been introduced
!-- with Selenium 2.14. Unfortunately, I could find no satisfactory explanation or workaround, and the problem has apparently not been fixed
!-- in later versions of Selenium (although the exception message was changed in version 2.22 to, "Element is not visible and cannot be
!-- interacted with". Even though the dropdown IS visible (to a human observer) apparently Selenium thinks it is not, and therefore it
!-- cannot be clicked upon. Perhaps this has to do with a loss of focus issue?

Narrative:
As a Southwest User who is currently logged in,
I want to view My Account by clicking on the Travel Tools | MySouthwest Account dropdown on the Home Page.
Since I am already logged in, I should be taken directly to the My Account page.

Scenario: A Southwest User (currently logged in) clicks on the Travel Tools | MySouthwest Account dropdown link and is taken directly to the My Account page.

Given I am a Rapid Rewards Member
And I am logged in and on the home page
When I click on the Travel Tools MySouthwest Account dropdown link
Then I am taken to the My Account page
