Verify that southwest.com user selecting Infant Flying link under the Air section in Global Nav bar going to correct URL.

Meta:
@flow air
@process view
@user anonymous
@traveler adult
@dyna_stubs

Narrative:
As a southwest.com user
I want to be able to access Infant Flying page
So that I can know more about Infant Flying proper documentation needed.

Scenario: Any visitor to southwest.com selects Infant Flying link on the Global Nav bar to access Infant Flying information page.

Given I am on the Home page
When I select Infant Flying under the Air section in Global Nav bar
Then I see the Infant Flying page