Verify that Purchase Page displays Trip Total, Funds Applied, and Total Due Now with their respective total amounts when users pay entire purchase amount with travel fund(s)

Meta:
@flow air
@process booking
@user anonymous
@traveler adult
@dyna_stubs
@not_live

Narrative:
As a .COM user, on the Purchase page
I want to see a better display of the Review Purchase Summary section at the bottom of the page after applying Travel Funds
So that the remaining amount owed (if any) is clearly shown

Scenario: User applies Travel Funds covering the whole amount of the purchase.

Given I am:

    |Field|Value|
    |adultPassengerCount|1|
    |seniorPassengerCount|0|

And I have the following itinerary data:

    |Field|Value|
    |itineraryType|One Way|
    |departureStation|SAT|
    |arrivalStation|LAS|
    |departureDate|+2|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|WannaGetAway|
    |outboundRouting|Nonstop|

And I have 1000 amount travel funds balance with 800 refundable amount and 200 nonrefundable amount
And I am on the purchase page
And I complete mandatory information for Passenger 1
When I apply a valid TravelFund with the full amount of the purchase
Then I see the Review Purchase Summary of my purchase