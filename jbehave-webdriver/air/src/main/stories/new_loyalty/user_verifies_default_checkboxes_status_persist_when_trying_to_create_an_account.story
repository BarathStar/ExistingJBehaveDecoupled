Verify the changed/unchanged check boxes persist and error messages are shown for the required fields that were not completed.

Meta:
@flow rr
@process loyalty
@user rr_member
@traveler adult
@dyna_stubs

Narrative:
In order to see the default values of the email subscription options changed/unchanged persist and error messages for mandatory fields not completed
As an Anonymous User
I want to create an Account without changing/changing the default value of the email subscription options and without completing the mandatory fields

Scenario: User verifies default and changed/unchanged check boxes status persist when trying to create an account and views error messages for mandatory fields that were not completed
Given I am an anonymous user
When I access Create an Account page
Then I see Click N' Save and In a Nutshell email subscription options are selected
And I see The Rapid Rewards Report and The Rapid Rewards E-mail Update email subscription options are selected
When I attempt to confirm the account creation
Then I see an error message for each mandatory field that was not entered
And I see Click N' Save and In a Nutshell email subscription options are selected
And I see The Rapid Rewards Report and The Rapid Rewards E-mail Update email subscription options are selected
When I select to subscribe to Click N Save email option
And I select to subscribe to In a Nutshell email option
And I select to unsubscribe to The Rapid Rewards Report email option
And I select to unsubscribe to The Rapid Rewards E-mail Update email option
And I attempt to confirm the account creation
Then I see an error message for each mandatory field that was not entered
And I see Click N' Save and In a Nutshell email subscription options are selected
And I see The Rapid Rewards Report and The Rapid Rewards E-mail Update email subscription options are not selected
