View service unavailable message in upcoming trips section

Meta:
@project RR2.0
@flow air
@process loyalty
@user anonymous
@not_passing draft
@message This story does not belong to any suite. It was drafted in order to not break the storymanager generator.


Narrative:
In order to view future air itineraries
As a customer
I want to view service unavailable message when service is down

Scenario: Customer views a message on snapshot page in upcoming trips section when service is down

Given I am a customer with upcoming trips
When I go to my snapshot page
Then I see service unavailable message in upcoming trips section
