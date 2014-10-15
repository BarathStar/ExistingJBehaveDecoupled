View destination finder as an anonymous user

Meta:
@project RR2.0
@flow air
@process loyalty
@user anonymous
@not_passing draft
@message This story does not belong to any suite. It was drafted in order to not break the storymanager generator.


Narrative: In order to view where points can take me
As an anonymous user
I want to view destination finder


Scenario: View destination finder as an anonymous user
Given I am an anonymous user on the Rapid Rewards overview page
When I click on where points can take me
Then I see destination finder
