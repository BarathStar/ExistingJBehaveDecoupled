Verify that southwest.com user selecting Airport Information link under the Air section in Global Nav bar will go to the correct URL

Meta:
@flow air
@traveler adult
@process view
@user anonymous
@dyna_stubs


Narrative:
As a southwest.com user
I want to be able to access Airport Information page
So that I can know more about flights and airports

Scenario: Any visitor to southwest.com selects Airport Information link on the Global Nav bar to access Airport Information page

Given I am a Southwest user at the homepage
When I select Airport Information under the Air section in Global Nav bar
Then I see the Airport Information page