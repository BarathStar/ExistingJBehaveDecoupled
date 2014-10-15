Rapid Rewards Snapshot page links

Meta:
@project RR2.0
@flow air
@process my southwest account
@user rapid reward member
@not_passing draft
@message This story does not belong to any suite. It was drafted in order to not break the storymanager generator.


Narrative:
In order to view my past, future and saved flights
As a Rapid Rewards Members
I want to view the my travel page


Scenario: saved flight

Given I am on the rapid reward my account page
And I have a upcoming flight reserved
When I click on my travel link
Then I can view my upcoming flight

Scenario: past flight

Given I am on the rapid reward my account page
When I click on my travel link
Then I can view my past trips

Scenario: saved flight

Given I am on the rapid reward my account page
And I have a upcoming flight saved
When I click on my travel link
Then I can view my saved flight
