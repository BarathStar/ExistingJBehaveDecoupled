Showing A Message When Points Are Not Enough To Book A Flight

Meta:
@suite pointsDotCom
@flow in_path_booking
@process loyalty
@user rr_member
@traveler adult
@project points_dot_com
@POINTSDOTCOM_BUY_GIFT_TRANSFER toggle is ON
@dyna_stubs
@not_passing click_blocking_timeout

Narrative:
In order to book a flight with points
As a Rapid Rewards Member without enough points
I want to attempt to book a flight

Scenario: Verify Points.com popup is display when you do not have enough points
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
And I click on buy points link on price page
Then I verify that the points.com popup is displayed