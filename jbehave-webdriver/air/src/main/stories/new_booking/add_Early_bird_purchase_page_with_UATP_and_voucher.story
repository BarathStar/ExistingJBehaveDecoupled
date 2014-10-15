Verify that Southwest.com user is able to pay an Air Booking that has Early Bird using UATP Credit Card and Travel Funds as form of payments on the Purchase Page.

Meta:
@flow air
@process purchase
@user anonymous
@traveler adult
@dyna_stubs
@not_live

Narrative:
In order to validate the Payment info section in purchase page
As a Southwest.com user doing an Air Booking
I want to be able to pay my Air and Early Bird products using a combination of UATP Credit Card and Travel Funds as form of payments
so that I complete my Air Booking including Early Bird

Scenario: User tries to pay Early Bird combining UATP Credit Card and Travel Funds

Given I am traveling as a:

    |Field|Value|
    |adultPassengerCount|1|
    |seniorPassengerCount|0|

And I have the following itinerary data:

    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|ISP|
    |arrivalStation|FLL|
    |departureDate|+2|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|WannaGetAway|
    |outboundRouting|Nonstop|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|Anytime|
    |inboundRouting|Nonstop|

And I am on the purchase page
And I have selected Yes to add Early Bird to my air purchase
And I have selected UATP credit card as my form of payment
And I apply a LUV Voucher as form of payment
When I fill in the Purchase form and submit
Then I see EarlyBird Check-In Purchased on air product on Existing purchases in trip