Log in using credentials (account number and password) from the header in New Home page.

Meta:
@dyna_stubs
@not_live
@user anonymous
@suite faultInjectionServiceTier

Narrative:
As Rapid Rewards Member
I want to login by entering valid account number and password credentials
And see an error message when the UnableToCreateTokenException occurs.

Scenario: User logs in from the Home page using valid account number and password
Given I am on the Home page
And I am a Rapid Rewards Member
And I am injecting a ./src/main/stories/fault_injection/btm/UnableToCreateTokenException.btm fault
When I click on the Login dropdown link
And I login as a RRMember with username and password
Then I verify the error message in the login dropdown that says "The Username/Account Number and/or Password are incorrect."
And I unload all byteman rules