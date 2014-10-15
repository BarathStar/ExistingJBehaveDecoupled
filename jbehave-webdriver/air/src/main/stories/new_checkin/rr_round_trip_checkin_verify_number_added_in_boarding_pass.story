Verify number is added in boarding pass check in a round trip as an adult

Meta:
@flow air
@process checkin
@passenger adult
@user rr_member
@dyna_stubs
@not_live

Narrative:
In order to receive my boarding pass with my Rapid Reward number
As an adult
I want to check in for a round trip flight

Scenario: number is added in boarding pass check in a round trip

Given I am traveling as a:
    |Field|Value|
    |adultPassengerCount|1|
    |seniorPassengerCount|0|

And I have the following itinerary data:
    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|DAL|
    |arrivalStation|AUS|
    |departureDate|+0|
    |returnDate|+1|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|BusinessSelect|
    |isValidForCheckin|true|
    |outboundRouting|Nonstop|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|BusinessSelect|
    |inboundRouting|Nonstop|

When I search and book a flight to be used for checkin, change or cancel flows from the search flights page
And I retrieve my reservation to checkin
And I enter my Rapid Rewards number on the Check In page
Then the Rapid Rewards update message is displayed
When I click checkin to create a boarding pass
And I click on the Continue button if I am in the MBP options page
Then I view my boarding pass
And I see my Rapid Reward Number in my boarding pass
Given I am on the Home page
When I go to the Checkin online page through the Air menu
And I retrieve my reservation to checkin
Then I will see my Rapid Rewards passenger information
When I click checkin to create a boarding pass
And I click on the Continue button if I am in the MBP options page
Then I view my boarding pass
And I see my Rapid Reward Number in my boarding pass