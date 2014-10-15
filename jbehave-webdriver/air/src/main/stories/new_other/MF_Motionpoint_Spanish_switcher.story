Verify that a response is being retrieved by MotionPoint when a user clicks on the language switcher link

Meta:
@flow air
@process other
@user anonymous
@traveler adult
@dyna_stubs
@project motionpoint

Narrative:
As an anonymous user
I want to see the translated site
So that I hit the home page switcher language link

Scenario:
Given I am on the Home page
When I click on the language switcher link
Then I should not be redirected from the homepage
