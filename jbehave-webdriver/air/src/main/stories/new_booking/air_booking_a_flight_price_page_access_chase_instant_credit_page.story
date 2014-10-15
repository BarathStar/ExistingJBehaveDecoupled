Verify that when a user is booking a flight and clicks on Chase Instant Credit banner on the Air Booking Price page, he accesses the new Chase Instant Credit page.

Meta:
@flow air
@process booking
@user anonymous
@traveler adult
@dyna_stubs
@not_passing draft

Narrative:
As an anonymous user in Southwest.com
I want to verify that I access the new Chase Instant Credit page from a banner on the Air Booking Price page
So that I know that URL was updated

Scenario: Validate accessing to new Chase Instant Credit page form Price page.
Given I am traveling as a:
    |Field|Value|
    |adultPassengerCount|1|
    |seniorPassengerCount|0|

And I have the following itinerary data:
    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|HOU|
    |departureDate|+1|
    |arrivalStation|DAL|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Anytime|
    |outboundRouting|Nonstop|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|Anytime|
    |inboundRouting|Nonstop|

And I get to the Price page
When I click on Chase Instant Credit banner in the price page
Then I see Chase Instant Credit page from the price page