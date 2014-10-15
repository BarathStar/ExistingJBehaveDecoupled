Air Change downgrade from Business Select to Anytime with 3PAX Verify correct Original Balance

Meta:
@flow air
@process change
@user anonymous
@traveler adult
@storyId BUG-2779
@suite bugpod

Narrative:
As a Customer with funds
I want the balance information of funds to match on all pages
So that information is consistent and accurate on all pages

Scenario:  In "Itinerary Change Confirmation" page, "Original Balance" is showing correctly.

Given I have booked a 3 pax Business Select PNR
And I change my reservation to Wanna Get Away
When I complete my reservation change
Then I see the Original Balance on the Itinerary Change Confirmation Page
