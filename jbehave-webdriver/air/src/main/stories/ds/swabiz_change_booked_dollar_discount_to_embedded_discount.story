SWABiz Change Itinerary with Dollar Off Discount to use Embedded Percent off

Meta:
@project DS
@flow air
@process change
@user sb_anonymous
@traveler adult
@storyId DCAIR-7214
@project_in_dev

Narrative:
In order to ensure cebs pricing works correctly
As an adult
I want to book a flight using a dollar off promo code
and then modify my itinerary to use the embedded percent off discount

Scenario: Modify Dollar Off to use Embedded Percent
Given I work for a company with embedded percent off discount
And I have anonymously logged into a SWABiz account
And I have the following itinerary data:
    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|DAL|
    |arrivalStation|BWI|
    |departureDate|+15|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Anytime|
    |outboundRouting|Change Planes|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|Anytime|
    |inboundRouting|Change Planes|

And I am using a dollars off promo code
When I enter my flight information
And I select and purchase a flight
Then I should see the SWABiz confirmation page
And I see the confirmation Fare Breakdown flyout has a fare discount of 10.00

When I attempt to change my Swabiz itinerary
And I select to change the inbound flight and continue to the Select New Flight page
And I select the new inbound flight and continue
Then I should see the Price Page with the new price
And I see the Fare Breakdown flyout has a fare discount greater than 10.00