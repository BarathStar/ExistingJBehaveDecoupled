Check the Travel Funds, so that I see the corresponding error messages when I do not complete the mandatory (required) fields.

Meta:
@flow other
@user anonymous
@dyna_stubs
@not_live

Narrative:
As an anonymous user
I want to check the Travel Funds when mandatory fields are not completed
In order to see the error messages

Scenario: User attempts to check the Travel Funds when mandatory fields are not completed
Given I am on the Home page
When I go to the View Reservation page through the Air menu
And I click on the View Travel Funds link
And I confirm to Check the Travel Funds
Then I see the Oops messages which inform me that the Passenger's names were not entered and the confirmation number is required
When I fill in the confirmation number and security codes with invalid length and leave empty the rest of the fields
And I confirm to Check the Travel Funds
Then I see the Oops message which inform me that the wrong data was entered and data in fields is missing
When I fill in the confirmation number and security codes and leave empty the rest of the fields
And I confirm to Check the Travel Funds
Then I see the Oops message which inform me that data in the fields is missing
When I fill in the Passenger's name fields and fill in the Voucher and Card number with invalid length
And I confirm to Check the Travel Funds
Then I see the Oops message which inform me the wrong data entered and the fields missing
When I fill in the Passenger's name fields and fill in the Voucher and Card number
And I confirm to Check the Travel Funds
Then I see the Oops message which inform me the fields missing
When I fill in all the fields with funds which do no exist
And I confirm to Check the Travel Funds
Then I see the Oops message which informs me that the funds were not retrieved
