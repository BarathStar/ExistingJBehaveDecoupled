Verify Gift Card, Travel Funds and LUV Vouchers apply the correct total

Meta:
@flow other
@user anonymous
@dyna_stubs
@not_live

Narrative:
As an anonymous user
I want to add a Gift Card, Travel Funds and Luv vouchers
In order to check the total balance

Scenario: User attempts to add a Gift Card, Travel Funds and Luv vouchers to check the total balance
Given I have a valid Travel Fund
And I have 4 valid LUV Voucher(s)
And I have 4 valid Giftcard(s)
And I am on the Home page
When I go to the View Reservation page through the Air menu
And I click on the View Travel Funds link
And I click on the Check Southwest LUV Voucher link
And I click on the Check southwestgiftcard link
And I add Travel Funds, LUV vouchers and Giftcards 3 times
Then I see the maximum allowed message for the Travel Funds, LUV Vouchers and GiftCards
When I fill in travel fund's fields with valid data
And I fill in giftcard's fields with valid data
And I fill in voucher's fields with valid data
And I confirm to Check the Travel Funds
Then I see the correct balances and totals for the Travel Funds, LUV Vouchers and GiftCards
