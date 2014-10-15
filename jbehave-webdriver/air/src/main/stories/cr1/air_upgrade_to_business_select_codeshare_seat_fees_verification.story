Seat fees verbiage should be displayed on Select Flights to Upgrade page for codeshare itinerary

Meta:
@project cr1
@airTranOnly
@flow air
@process other
@traveler adult
@user anonymous
@storyId DCQA-25, ZR-1132
@seatSelection
@dyna_stubs
@project_in_dev

Narrative:
As an Adult
I want to book a codeshare itinerary
So that I can see seat fees verbiage in Select Flights to Upgrade page

Scenario: View seat fees verbiage in Select Flights to Upgrade page for a codeshare itinerary

Given I have selected the following itinerary data:

    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|ATL|
    |arrivalStation|BWI|
    |departingFlight_carrierCode|FL|
    |departingFlight_fareClass|Anytime|
    |outboundRouting|Nonstop|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|Anytime|
    |inboundRouting|Nonstop|
    |earlyBirdPurchased|false|
    |departureDate|+5|

When I search and book a flight to be used for checkin, change or cancel flows from the search flights page
And I get to the business select page
And I retrieve my reservation through Stand Alone Path
Then I see "Select Flights to Upgrade" page
And I should see the seat fees verbiage
