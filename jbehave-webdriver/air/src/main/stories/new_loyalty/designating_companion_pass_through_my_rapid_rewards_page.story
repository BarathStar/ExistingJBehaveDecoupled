Verify a prominent message is displayed in "My Account" --> "My Rapid Rewards" Tab for a RR Member Companion pass status and has NOT setup his companion pass already.

Meta:
@flow rr
@process other
@user rr_member
@traveler adult
@not_live
@dyna_stubs

Narrative:
As a Rapid Rewards Companion pass
I want to see a new message row in "My Rapid Rewards" Tab.
So that I can remember that I have to setup my Companion pass

Scenario: "You have earned a Companion Pass" is not longer displayed

Given I am a Rapid Rewards Member with Companion Pass status achieved without a designated Companion Pass
And I am logged in and on My Rapid Rewards page
When I enter the Companion pass Information accessing through My Rapid Rewards Tab
Then I will see that I do not have any message about Companion Pass in My Rapid Rewards Page