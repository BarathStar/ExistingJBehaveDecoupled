Verify the widget to add phone number at personal information is not filled in with random number when attempt to add new number as Rapid Reward member

Meta:
@flow rr
@process other
@user rr_member
@passenger adult
@dyna_stubs
@not_live

Narrative:
As an user
I want to see empty the input text box when attempt to add another phone number
So that

Scenario: Verify if the new input text on contact information in rapid reward preference is empty when attempt to add another phone number

Given I am logged in as Rapid Rewards member on the Rapid Rewards Account page
When I select My Preferences tab
And I access my account's Contact Information
And I click add/edit for "phone"
And I attempt to add another phone number
Then I should see input text box for new phone number is empty