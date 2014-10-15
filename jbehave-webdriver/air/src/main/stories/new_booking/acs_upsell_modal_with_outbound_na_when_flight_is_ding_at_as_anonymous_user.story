Validate BS upsell modal with a n/a for Ding segment when flight is Ding - AT as anonymous user in .com

Meta:
@flow air
@process booking
@user anonymous
@traveler adult
@removedFromAirbooking
@dyna_stubs
@not_live
@acs_bs_modal

Narrative:
As an anonymous user in Southwest.com
I want to verify that I see BS upsell modal with a 'n/a' for Ding segment when flight is Ding - AT
So that I know that upsell doesn't apply for Ding.

Scenario: Validate BS upsell modal with a 'n/a' for Ding segment when flight is Ding - AT.

Given I have selected the following itinerary data:
    |Field|Value|
    |adultPassengerCount|1|
    |seniorPassengerCount|0|
    |itineraryType|round-trip|
    |departureStation|HOU|
    |departureDate|+1|
    |returnDate|+5|
    |arrivalStation|DAL|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Ding|
    |outboundRouting|Nonstop|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|Anytime|
    |inboundRouting|Nonstop|
When I search and select my flights and attempt to continue to the Price page
Then I see that the upsell modal displays only the outbound flight as not eligible for upgrade to BS
