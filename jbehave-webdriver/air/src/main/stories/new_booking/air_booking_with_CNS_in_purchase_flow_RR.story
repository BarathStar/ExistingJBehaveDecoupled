Subscribe to Click N'Save during purchase flow, and nothing breaks

Meta:
@project 5.22ffClassicRetirement
@project_in_dev
@flow air
@process booking
@traveler adult
@user rr_member
@not_live

Narrative:
In order to subscribe to the Click 'N Save mailing list
As a Rapid Rewards Member
I want to subscribe to the Click 'N Save mailing list during the purchase flow

Scenario: Subscribe to Click 'N Save during purchase flow
Given I am logged in as Rapid Rewards member
And I am traveling as a:

    |Field|Value|
    |adultPassengerCount|1|
    |seniorPassengerCount|0|

And I have the following itinerary data:

    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|HOU|
    |arrivalStation|DAL|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Anytime|
    |outboundRouting|Nonstop|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|Anytime|
    |inboundRouting|Nonstop|

And I am on the purchase page
When I fill out the purchase form
And I select the Click N'Save checkbox
And I click on the Purchase button
Then I receive an air confirmation number
