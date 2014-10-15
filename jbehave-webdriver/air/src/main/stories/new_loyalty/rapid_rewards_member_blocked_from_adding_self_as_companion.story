Block a Rapid Rewards member from adding self as companion

Meta:
@flow rr
@process other
@user rr_member
@traveler adult
@not_live
@dyna_stubs

Narrative:
As a Rapid Rewards member with a Companion pass
When I try to add myself as a companion
I should see an Oops message on the companion status page

Scenario: Verify that when a RR Member tries to add self as companion, an Oops message is displayed

Given I am a Rapid Rewards Member with Companion Pass status achieved without a designated Companion Pass
And I am logged in and on the snapshot page
When I enter the Companion pass Information which matches my info through My Snapshot Tab
Then I see an oops message on the Companion Pass status page with the message: Sorry, you can no longer designate yourself as your own companion online. Please designate another person and resubmit the request or contact Rapid Rewards at 1-800-455-5764 for assistance.
