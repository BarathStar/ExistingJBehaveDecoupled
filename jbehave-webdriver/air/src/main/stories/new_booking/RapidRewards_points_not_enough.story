Showing A Message When Points Are Not Enough To Book A Flight

Meta:
@flow air
@process booking
@user rr_member
@traveler adult
@dyna_stubs
@not_live
@points_dot_com_off

Narrative:
In order to book a flight with points
As a Rapid Rewards Member without enough points
I want to attempt to book a flight

Scenario: Message advising that points are not enough
Given I have the following itinerary data:
    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|DAL|
    |arrivalStation|HOU|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Anytime|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|Anytime|

And I am a Rapid Rewards Member passenger
When I log in from the account sidebar at the Search Flights page
And I am searching flights from flight search page
And I change my search to points
And I attempt to select a flight and purchase with not enough points
Then I verify the blue informational message for not enough points
And I verify the Continue button is disabled
When I select purchase more points
And I click on Air tab
And I click on checkout button
Then I verify the blue informational message for not enough points
And I verify the Continue button is disabled