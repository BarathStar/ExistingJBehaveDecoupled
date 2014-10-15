Extend Awards For Rapid Rewards member

Meta:
@project RR2.0
@flow awards
@process booking
@user rr_member
@not_passing draft
@message This story does not belong to any suite. It was drafted in order to not break the storymanager generator.


Narrative:
In order to extend dates on an existing award
As a Rapid Rewards Member
I want to reissue awards
and receive a reissue receipt


Scenario: Rapid Rewards Member extends standard award

Given I am a Rapid Rewards Member with at least one standard award
When I reissue a standard award
Then I receive a reissue receipt

Scenario: Rapid Rewards Member extends freedom award

Given I am a Rapid Rewards Member with at least one freedom award
When I reissue a freedom award
Then I receive a reissue receipt

Scenario: Rapid Rewards Member extending without selection

Given I am a Rapid Rewards Member with at least one award
When I reissue awards without selecting an award
Then I see an oops message

Scenario: Rapid Rewards Member extending more than 10 awards

Given I am a Rapid Rewards Member with more than 10 awards
When I reissue more than 10 awards
Then I see an oops message