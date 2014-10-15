Verify all the breadcrumbs under "My Preferences" section

Meta:
@flow rr
@process loyalty
@user rr_member
@traveler adult
@dyna_stubs

Narrative:
In order to be enrolled to the Rapid Rewards program and see all the different breadcrumbs under "My Preferences" section
As an Anonymous User
I want to create a Rapid Rewards account and check all the options available at "My Preferences" section

Scenario: User creates a Rapid Rewards account and views all the different breadcrumbs under "My Preferences" section
Given I am a Southwest User at "Create an Account" page
When I complete all the mandatory fields
And I select to create a Rapid Rewards account
And I confirm the account creation
Then I see the Member Account Confirmation page
And I see the option where to go with my Rapid Rewards points
And I see the option to book Air, Car and Hotel products
And I see my first and last name in the Rapid Rewards card
And I see my Account Number in the account sidebar
And I see my Account Number in the Rapid Rewards card
And I see my Account Number under the Congratulations message
And I see that all the Account Numbers displayed match
When I decide to complete my account's Preferences
Then I see the "Username & Password" page
And I see the breadcrumbs shown in the following order: "My Account -> My Preferences -> Username & Password"
When I access my account's Contact Information
Then I see the "Contact Information" page
And I see the breadcrumbs shown in the following order: "My Account -> My Preferences -> Contact Information"
When I access my account's Communication Preferences
Then I see the "Communication Preferences" page
And I see the breadcrumbs shown in the following order: "My Account -> My Preferences -> Communication Preferences"
When I access my account's Payment Information
Then I see the "Payment Information" page
And I see the breadcrumbs shown in the following order: "My Account -> My Preferences -> Payment Information"