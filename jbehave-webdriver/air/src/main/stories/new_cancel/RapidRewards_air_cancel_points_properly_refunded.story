Verify points are refunded to holder's account on cancel confirmation page as A Rapid Rewards Member (Points)

Meta:
@flow air
@process cancel
@user rr_member
@passenger adult
@dyna_stubs
@not_live
@not_passing draft

Narrative:
As a Rapid Rewards Member
In order to verify that an itinerary was cancelled
I want to receive a confirmation after cancelling the flight
So that

Scenario: Verify points are refunded to holder's account in cancellation
Given I have the following itinerary data:
    |Field|Value|
    |itineraryType|One Way|
    |departureStation|DAL|
    |arrivalStation|HOU|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Anytime|

And I have enough points to book a flight
When I log in from the account sidebar at the Search Flights page
And I am searching flights from flight search page
And I change my search to points
And I select, price and view the Purchase page for a flight
And I fill out the purchase form as a logged in user
And I change the credit card owner
And I continue to Confirmation page from Purchase page
And I cancel the flight
Then I see on the Cancel Confirmation Page that points will be returned to passenger's account