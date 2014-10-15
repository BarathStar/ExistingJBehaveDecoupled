Validate a user can create a new Flight Status Notification for a flight departing within ten days from the current date.

Meta:
@flow air
@process flight_status
@user anonymous
@traveler adult
@dyna_stubs

Narrative:
In order to validate Flight Status Notification calendar within the 'Lookup Flight Number' popup
As a user
I want to verify that I am able to create a notification within the next 10 days

Scenario: User attempts to create a flight status notification within 10 days using the 'Lookup Flight Number' popup
Given I have the following itinerary data:
          |Field|Value|
          |departureStation|DAL|
          |arrivalStation|HOU|
          |departureDate|+5|

And I am on the Create Notification tab on Flight Status page
When I select 'Lookup Flight Number' and attempt to select a date within 10 days
Then I should be able to pick the date for my flight status notification