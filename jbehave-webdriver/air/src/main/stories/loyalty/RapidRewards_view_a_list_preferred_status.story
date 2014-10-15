Member Reaches A List Preferred

Meta:
@project RR2.0
@flow air
@process loyalty
@user anonymous
@not_passing draft
@message This story does not belong to any suite. It was drafted in order to not break the storymanager generator.


Narrative:

In order to see if that A-List Preferred Status has been achieved
As a Rapid Rewards Member
I want to see my tier status shows A-List Preferred

Scenario: Member Reaches A-List Preferred

Given I am a Rapid Rewards Member who has achieved A-List Preferred status
When I login to my account
Then I view my A-List Preferred status in the account bar
