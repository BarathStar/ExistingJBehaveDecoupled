Verify that southwest.com user selecting Special Assistance link in Customer Services section on the Footer will go to correct URL.

Meta:
@flow air
@process view
@user anonymous
@traveler adult
@dyna_stubs

Narrative:
As a southwest.com user
I want to be able to access Special Assistance page
So that I can know more about information needed for persons with disabilities.

Scenario: Any visitor to southwest.com selects Special Assistance link on the Footer to access Special Assistance information page.

Given I am a Southwest user at the homepage
When I select Special Assistance under the Customer Service section on the page footer
Then I see the Special Assistance page