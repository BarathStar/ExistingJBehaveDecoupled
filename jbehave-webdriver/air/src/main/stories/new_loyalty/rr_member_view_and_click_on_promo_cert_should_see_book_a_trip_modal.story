Verify book a trip modal is displayed when user clicks on promotional certificate row name.

Meta:
@flow air
@process booking
@user rr_member
@traveler adult
@project JL_CR20_X_V_PROMOCERT
@dyna_stubs
@not_live
@project_in_dev
@storyid ZR-433

Narrative:
As a RapidRewards Member
I want to see the book a trip modal display after login into my account and try to click on the name link of the promotional certificates

Scenario: RR Member views book a trip modal when he clicks on the book a trip on the certificate-details page
Given I am a Rapid Rewards Member with two active X and V promotional certificates in my account
When I log in from the account sidebar at the Search Flight page
And I expand My Rapid Rewards section
Then I see my promotion with its expiration date
When I request to see my promotions from the account bar
Then I view my active promotions in my account
And I see the promotion name link
When I click on the promotion name link
Then I see Promotional Certificate Details page
When I click on the book a trip button on promotion details page
Then I see the book a flight modal
