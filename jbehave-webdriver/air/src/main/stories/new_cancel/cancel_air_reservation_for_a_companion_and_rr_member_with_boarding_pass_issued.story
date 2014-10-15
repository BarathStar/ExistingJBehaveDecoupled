Cancel an Air reservation for a member and his companion passenger which boarding passes have already been issued, so that I see the boarding passes information for both passengers is shown in the Cancel page.

Meta:
@flow air
@process cancel
@user rr_member
@traveler adult
@dyna_stubs
@not_live

Narrative:
In order to see both boarding passes already issued on Cancel Air page
As an Anonymous User
I want to retrieve the Air reservation for a member and his companion passenger

Scenario: User tries to cancel an Air reservation for a companion and himself which boarding passes have already been issued
Given I have the following itinerary data:
    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|DAL|
    |arrivalStation|AUS|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Anytime|
    |outboundRouting|Nonstop|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|Anytime|
    |inboundRouting|Nonstop|

And I have an air reservation for a member with companion pass achieved and boarding pass issued
And I have an air reservation for a companion passenger with boarding pass issued
When I retrieve the Air reservation to cancel it
Then I see the boarding pass information for both, the rapid rewards member and his companion passenger