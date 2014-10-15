Verify that Southwest.com users are able to subscribe to departure Flight Status Update beyond scheduled up to estimated departure 
time when their flight is delayed when toggle NEW_FLIGHT_STATUS_NOTIFICATION_RULES is ON.

Meta:
@flow air
@process flight_status
@traveler adult
@user anonymous
@dyna_stubs
@not_live
@not_passing draft NEW_FLIGHT_STATUS_NOTIFICATION_RULES toggle is OFF by default

Narrative:
As a southwest .com user 
I want to be able to subscribe to Flight Status Notifications beyond scheduled up to estimated departure time when my flight is delayed 
So that I can always have a notification when my flight is not ontime

Scenario: Verify that Southwest.com users are able to subscribe to departure Flight Status Update beyond scheduled up to estimated departure time when their flight is delayed
Given I have the following itinerary data:
          |Field|Value|
          |departureStation|DAL|
          |arrivalStation|AUS|
          |departureDate|+0|

And I am on the Create Notification tab on Flight Status page
And I select to subscribe to departure notifications
When I enter the information for a delayed flight beyond its scheduled time and submit it
Then I should see my departure notification successfully created based on the estimated departure time of my delayed flight