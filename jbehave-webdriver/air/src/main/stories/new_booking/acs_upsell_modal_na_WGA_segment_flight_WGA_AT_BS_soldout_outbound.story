Validate BS upsell modal with a 'n/a' for WGA segment when flight is WGA - AT and BS is sold out for outbound, as anonymous user in .com

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
I want to verify that I see BS upsell modal with a 'n/a' for WGA segment when flight is WGA - AT and BS is sold out for outbound
So that I know that upsell doesn't apply for WGA in this situation.

Scenario: Validate BS upsell modal with a 'n/a' for WGA segment when flight is WGA - AT and BS is sold out for outbound.

Given I am:
    |Field|Value|
    |adultPassengerCount|1|
    |seniorPassengerCount|0|

And I have selected the following itinerary data:
    |Field|Value|
    |itineraryType|Round Trip|
    |departureDate|+1|
    |departureStation|IND|
    |arrivalStation|DAL|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|WannaGetAway|
    |outboundRouting|Nonstop|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|Anytime|
    |inboundRouting|Nonstop|

And BS is sold out for the outbound flight
When I search and select my flight and attempt to continue to the Price page
Then I see that the upsell modal displays only the outbound flight as not eligible for upgrade to BS