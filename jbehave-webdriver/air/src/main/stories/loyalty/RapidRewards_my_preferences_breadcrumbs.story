My Preferences Navigation

Meta:
@project RR.20
@flow air
@process my southwest account
@user anonymous
@not_passing draft
@message This story does not belong to any suite. It was drafted in order to not break the storymanager generator.


Narrative:
In order to navigate My Preferences
As a customer
I want to have options to navigate to my information


Scenario: User clicks Contact Information

Given I am a Rapid Rewards Member on the preference page
When I choose to navigate the Contact Information link
Then I should see the Contact Information page

Scenario: User clicks Communication Preferences

Given I am a Rapid Rewards Member on the preference page
When I select the Communication Preferences link
Then I should see the Communication Preferences page

Scenario: User clicks Payment Information

Given I am a Rapid Rewards Member on the preference page
When I choose to navigate the Payment Information link
Then I should see the Payment Information page
