Validate a user can not create flight notification beyond 10 days

Meta:
@flow air
@process flight_status
@user anonymous
@traveler adult
@dyna_stubs

Narrative:
In order to validate Flight Status Notification calendar
As a user
I want to verify that I am not able to create a notification for a date further to ten days

Scenario: User attempts to create a flight status notification further than 10 days
Given I have the following itinerary data:
          |Field|Value|
          |departureStation|DAL|
          |arrivalStation|HOU|
          |departureDate|+11|

And I am on the Create Notification tab on Flight Status page
When I attempt to create a notification for a date further than 10 days
Then I should not be able to create the flight status notification