Apply travel funds from discounted flight to a new flight

Meta:
@project DS
@flow air
@process change
@user sb_anonymous
@traveler adult
@storyId DCAIR-7215
@project_in_dev

Narrative:
In order to use my travel funds
As an adult
I want to book a flight using a discount and then apply the travel funds

Scenario: Apply Travel Funds
Given I have anonymously logged into a SWABiz account
And I have the following itinerary data:
    |Field|Value|
    |itineraryType|One Way|
    |departureStation|HOU|
    |arrivalStation|MDW|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Anytime|
    |outboundRouting|Nonstop|

And I am using a percent off promo code
When I enter my flight information
And I select and purchase a flight
Then I should see the SWABiz confirmation page
And I see the confirmation Fare Breakdown flyout has a fare discount greater than 15.00

When I cancel the flight and hold funds

Given I have the following itinerary data:
    |Field|Value|
    |itineraryType|One Way|
    |departureStation|HOU|
    |arrivalStation|BWI|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Anytime|
    |outboundRouting|Change Planes|

When I book a new swabiz flight with no promo code
And I select flights and continue to the Purchase page
And I apply retained travel funds and fill in all required fields
And I click on the Purchase button
Then I receive an air confirmation number
And I see the confirmation Fare Breakdown flyout has no fare discount
