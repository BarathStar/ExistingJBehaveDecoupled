Verify that users on the Purchase Page see an updated billing/payment section when full amount of purchase is covered with travel fund(s)

Meta:
@flow air
@process booking
@user anonymous
@traveler adult
@dyna_stubs 
@not_live

Narrative:
In order to apply travel funds on the payment module on purchase page
As a .COM user, on the Purchase page I would like to see a better display of the Billing section after applying Travel Funds 
so that the remaining required steps for the purchase (i.e. remaining balance payments, billing information) are more clearly defined.

Scenario: User applies Travel Funds covering the whole amount of the purchase.

Given I am:

    |Field|Value|
    |adultPassengerCount|1|
    |seniorPassengerCount|0|

And I have the following itinerary data:

    |Field|Value|
    |itineraryType|One Way|
    |departureStation|HOU|
    |arrivalStation|DAL|
    |departureDate|+1|
    |arrivalStation|DAL|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|WannaGetAway|
    |outboundRouting|Nonstop|

And I have 1000 amount travel funds balance with 1000 refundable amount and 200 nonrefundable amount
And I am on the purchase page
And I complete mandatory information for Passenger 1
When I apply a valid TravelFund with the full amount of the purchase
Then I see billing information displayed

