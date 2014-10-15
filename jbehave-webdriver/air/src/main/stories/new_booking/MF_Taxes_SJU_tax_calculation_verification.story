Tax verification for new locations - San Juan

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
In order to verify the taxes
As an adult
I want to view the full fare price of a flight that includes San Juan location in the Select Flights grid


Scenario: User sees the total price with taxes and fees included

Given I am traveling as a:

    |Field|Value|
    |adultPassengerCount|1|
    |seniorPassengerCount|0|

And I have the following itinerary data:

    |Field|Value|
    |itineraryType|One Way|
    |departureStation|HOU|
    |arrivalStation|DAL|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|BusinessSelect|
    |outboundRouting|Nonstop|
    |outboundBaseFare|450|
    |outboundDiscountBaseFare|450|
    |outboundTaxes_USPFC|7.5|
    |outboundTaxes_USSECURITY|2.5|
    |outboundTaxes_USINTARR|16.7|

When I am on the select flights page
Then I see that the select flights page displays the total price correct