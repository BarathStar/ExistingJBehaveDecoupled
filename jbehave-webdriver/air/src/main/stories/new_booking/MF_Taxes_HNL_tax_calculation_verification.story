Tax verification for new locations - Honolulu

Meta:
@flow air
@process booking
@user anonymous
@traveler adult
@project multifleet
@removedFromAirbooking
@dyna_stubs
@not_live

Narrative:
In order to verify the taxes and fees
As an adult
I want to view the full fare price of a flight that includes Honolulu location in the Select Flights grid


Scenario: User sees the total price with taxes and fees included

Given I am traveling as a:

    |Field|Value|
    |adultPassengerCount|1|
    |seniorPassengerCount|0|

And I have the following itinerary data:

    |Field|Value|
    |itineraryType|One Way|
    |departureStation|LAX|
    |arrivalStation|DAL|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|BusinessSelect|
    |outboundRouting|Nonstop|
    |outboundBaseFare|400|
    |outboundDiscountBaseFare|400|
    |outboundTaxes_USPFC|7.5|
    |outboundTaxes_USSECURITY|2.5|
    |outboundTaxes_USZP|3.8|
    |outboundTaxes_USTRANSP|40|
    |outboundTaxes_USAKHI|8.4|

When I am on the select flights page
Then I see that the select flights page displays the total price correct