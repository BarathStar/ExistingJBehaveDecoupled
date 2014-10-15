validate flight status can be checked within 10 days

Meta:
@flow air
@process flight_status
@user anonymous
@global_nav_regression
@traveler adult
@dyna_stubs

Narrative:
In order to validate Flight Status Notification calendar
As a user
I want to verify that I am able to create a notification within the next 10 days

Scenario: User attempts to create a flight status notification within 10 days
Given I have the following itinerary data:
          |Field|Value|
          |departureStation|DAL|
          |arrivalStation|HOU|
          |departureDate|+5|

And I am on the Create Notification tab on Flight Status page
When I attempt to create a notification within 10 days
Then I should be able to pick the date for my flight status notification