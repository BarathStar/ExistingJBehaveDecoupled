validate flight status can not be checked 10 days out

Meta:
@flow air
@process flight_status
@user anonymous
@traveler adult
@dyna_stubs

Narrative:
In order to validate Edit Flight Status Notification calendar
As a user
I want to verify that I am not able to create a notification for a date further to ten days

Scenario: User attempts to edit a flight status notification further than 10 days
Given I have the following itinerary data:
          |Field|Value|
          |departureStation|DAL|
          |arrivalStation|HOU|
          |departureDate|+11|

And I am on the View or Edit Notification tab on Flight Status page
When I attempt to edit a notification further than 10 days
Then I should not be able to edit the flight status notification
