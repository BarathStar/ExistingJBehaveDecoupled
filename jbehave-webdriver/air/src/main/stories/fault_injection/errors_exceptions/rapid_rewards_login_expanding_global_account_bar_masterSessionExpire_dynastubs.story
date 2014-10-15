Injecting SessionExpired exception in sessionEnpoint at the right nav expanding

Meta:
@project GlobalAccountBar
@suite faultInjectionServiceTier
@process login
@flow all
@traveler adult
@dyna_stubs
@not_live
@not_passing draft

Narrative:
As a system professional
I want to inject a SessionExpired exception
In order to ensure the proper error handling on right nav global account bar

Scenario: User logs in on the account bar, then click account snapshot and expand RapidRewards bar when master session expired

Given I am a Rapid Rewards Member passenger
And I log in from the account sidebar at and click my account link
And I am injecting a ./src/main/stories/fault_injection/btm/RetrieveMasterSessionStub_SessionExpired.btm fault
When I expand My Rapid Rewards section
Then I should see an Oops message for session expired
And I unload all byteman rules
