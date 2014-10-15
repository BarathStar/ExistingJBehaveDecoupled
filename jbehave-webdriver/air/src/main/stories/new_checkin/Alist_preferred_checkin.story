Check-in round-trip anytime air ticket for 1 Rapid Rewards A-List Preferred member

Meta:
@flow air
@process checkin
@user rr_alist_preferred
@global_nav_regression
@traveler adult
@dyna_stubs
@not_live

Narrative:
In order to get my boarding pass
As an A list Preferred member
I want to complete a check in and see A List Preferred status printed on boarding pass

Scenario: A-List Preferred Member Checking in
Given I am logged in as a AlistPreferred Rapid Rewards member on the Search Flights page
And I am traveling as a:
    |Field|Value|
    |adultPassengerCount|1|
    |seniorPassengerCount|0|

And I have the following itinerary data:
    |Field|Value|
    |itineraryType|One Way|
    |departureStation|HOU|
    |arrivalStation|DAL|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Anytime|
    |isValidForCheckin|true|

When As a Rapid Reward user, I search and book a flight to be used for checkin, change or cancel flows from the search flights page
And I retrieve my reservation to checkin
And I click checkin to create a boarding pass
And I click on the Continue button if I am in the MBP options page
Then I view my A List Preferred boarding pass
