Try to create account with invalid company ID, so that I am taken back to Swabiz Create Account page with oops message

Meta:
@flow rr
@process loyalty
@user rr_member
@traveler adult
@dyna_stubs

Narrative:
In order to create an account with a companyID
As a SWABIZ Traveler
I want to enter the companyID and user details and try to create the account

Scenario: SWABIZ Traveler gets oops message on Swabiz Create Account page when trying to create account with a invalid companyID
Given I use an invalid companyID
When I try to enroll for a Rapid Rewards account through SWABIZ with the invalid companyID
Then I see the oops message with invalid companyId text on the SWABIZ create account page