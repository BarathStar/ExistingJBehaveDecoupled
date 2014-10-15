Verify that southwest.com user sees Oops error messages when submitting Best Rate Guarantee form with invalid Phone Number, City/State of Hotel, Amount Paid For Your Stay, Name, Hotel Confirmation Number, and E-mail.

Meta:

@flow hotel
@traveler adult
@process other
@user anonymous
@dyna_stubs

Narrative:
As a southwest.com user
I want to see error messages for invalid Phone Number, City/State of Hotel, Amount Paid For Your Stay, Name, Hotel Confirmation Number, and E-mail
So that I can correct them.

Given I am a user on BRG page
And I complete phone number field with invalid data on BRG page
And I complete city/state of hotel with invalid data on BRG page
And I complete amount field with invalid amount on BRG page
And I complete name with invalid data on BRG page
And I complete hotel confirmation number with invalid data on BRG page
And I complete email field with invalid email on BRG page
When I submit the BRG form
Then I see invalid phone number, city/state of hotel, amount paid for your stay, name, hotel confirmation number, and e-mail oops error messages on BRG form