Check my Gift Cards balance, so that I see the corresponding error messages when I provide incorrect or incomplete information.

Meta:
@flow other
@user anonymous
@dyna_stubs
@not_live

Narrative:
In order to get the expected error messages
As an anonymous user
I want to check the Gift Cards balance providing incorrect/incomplete information

Scenario: User attempts to check the Gift Cards Balance providing incorrect or incomplete information
Given I am on the Home page
When I click on the southwestgiftcard link from the Global Nav Footer
And I click on the check your Balance tab
And I click three times on the link Check another southwestgiftcard
Then I should not see the link named Check another southwestgiftcard
When I fill in the Security Codes with invalid length and leave the card numbers empty
And I confirm to check the balances
Then I see the Oops messages which inform me that the card numbers were not entered and the security code lengths are invalid
When I fill in the security codes with valid length and leave empty the card numbers
And I confirm to check the balances
Then I see the Oops message which inform me that the card numbers were not entered
When I fill in the Card Numbers with invalid length and leave the security codes empty
And I confirm to check the balances
Then I see the Oops messages which inform me that the security codes were not entered and the card number lengths are invalid
When I fill in the Card Numbers with valid length and leave the security codes empty
And I confirm to check the balances
When I enter four giftcards which do not exist
And I confirm to check the balances
Then I see the Oops message informing me that the giftcards were not found