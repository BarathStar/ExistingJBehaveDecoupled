Verify Extra Fee Message in Travel Funds Section for Unaccompanied Minor Air Purchase

Meta:
@flow air
@process booking
@user anonymous
@traveler UM
@project SWAT
@dyna_stubs
@project_in_dev
@not_live

Narrative:
As an unaccompanied minor
I am purchasing an air ticket using travel funds
So that I want to see a message in travel funds section warning me about the extra fee

Scenario: Verify if UM message is shown in travel funds section

Given I am:

    |Field|Value|
    |adultPassengerCount|1|
    |seniorPassengerCount|0|

And I have the following itinerary data:

    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|HOU|
    |arrivalStation|DAL|
    |departureDate|+1|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|WannaGetAway|
    |outboundRouting|Nonstop|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|WannaGetAway|
    |inboundRouting|Nonstop|

And I have 1000 amount travel funds balance with 1000 refundable amount and 200 nonrefundable amount
And I am on the purchase page
And I complete mandatory information for Passenger 1
And I apply a valid TravelFund with the full amount of the purchase
When I fill passenger and parent guardian information and the payment method is <payment>
Then I should see information about UM in travel funds section

Examples:
|payment|
|Travel_Funds|