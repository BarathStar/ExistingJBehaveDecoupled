Verify if the account number are fill and the remember me checkbox still selected when return to page before login on the home page as rapid reward member

Meta:
@flow air
@process checkin
@passenger adult
@user rr_member
@dyna_stubs
@not_live

Narrative:
In order to verify if the remember me checkbox work
As an adult
I want to see my account number filled and the checkbox remember me selected

Scenario: Verify if the account number is filled and the remember me checkbox still selected when return to page before login on the home page

Given I am a Rapid Rewards Member
And I fill in account number and password from the home page
And I check the option "Remember Me"
And I click on login button
When I come back to the website
Then I will see my account number populated and remember me check box checked