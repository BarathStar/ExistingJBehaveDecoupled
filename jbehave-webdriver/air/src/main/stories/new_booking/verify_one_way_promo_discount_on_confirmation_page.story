Verify promo code discount on confirmation page

Meta:
@flow air
@process booking
@user anonymous
@passenger adult
@dyna_stubs
@not_live
@not_passing draft

Narrative:
As an adult
I want to see the the promo code discount on the confirmation page

Scenario: Verify promo code discount on the confirmation page

Given I have a Discount like:
    |Field|Value|
    |id|12345|
    |code|PROMONOW|
    |isUnlimited|true|
    |usageMaxLimit|1000|
    |saleBeginDateTime|Today|
    |saleEndDateTime|60|
    |isRestrictRapidReward|false|
    |messageText|Enjoy the discount|
    |type|MULTI_USE|
    |state|ACTIVE|
    |webSite|SOUTHWEST|
    |supportedFareType|DOLLARS|
    |cat25Mapping|PROMONOW|
    |isRoundTripRestriction|false|
And I have the following itinerary data:
    |Field|Value|
    |itineraryType|One Way|
    |departureStation|BOI|
    |arrivalStation|HOU|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Anytime|
    |outboundRouting|Nonstop|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|Anytime|
    |inboundRouting|Nonstop|
    |randomizeBaseFare|false|
    |promoCode|PROMONOW|
When I book a flight
Then I see a fare discount on confirmation flyout equal to 10.00