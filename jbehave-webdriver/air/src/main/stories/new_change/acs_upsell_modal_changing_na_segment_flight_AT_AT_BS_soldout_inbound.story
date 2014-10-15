Validate BS upsell modal with a 'n/a' for the inbound segment when changing a RT intinerary and selecting new flights with AT - AT fares and BS is sold out for inbound, as anonymous user in .COM

Meta:
@flow air
@process change
@user anonymous
@traveler adult
@dyna_stubs
@not_live
@acs_bs_modal

Narrative:
As an anonymous user in Southwest.com
I want to see 'n/a' for the inbound segment in the BS Upsell Modal when changing a RT itinerary and selecting new flights with AT as outbound fare and AT as inbound fare and BS is sold out for inbound
So that I know that upsell does not apply for AT inbound flight in this situation.

Scenario: Validate BS upsell modal with a 'n/a' for the inbound segment when changing a RT intinerary and selecting new flights with AT - AT fares and BS is sold out for inbound

Given I am:
    |Field|Value|
    |adultPassengerCount|1|
    |seniorPassengerCount|0|

And I have selected the following itinerary data:
    |Field|Value|
    |itineraryType|Round Trip|
    |departureDate|+1|
    |departureStation|MDW|
    |arrivalStation|LAS|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|WannaGetAway|
    |outboundRouting|Nonstop|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|WannaGetAway|
    |inboundRouting|Nonstop|

And I have a flight booked
And I am changing the following itinerary data to:

    |Field|Value|
    |departingFlight_fareClass|Anytime|
    |arrivingFlight_fareClass|Anytime|

And BS is sold out for the inbound flight
When I retrieve my reservation for change and select a new price
Then I see that the upsell modal displays only the inbound flight as not eligible for upgrade to BS