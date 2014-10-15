Cancel my flight and have the award returned to the corresponding account.

Meta:
@flow air
@process cancel
@user rr_member
@traveler adult
@dyna_stubs

Narrative:
In order to cancel a reservation purchased with one transitional award
As an Anonymous User
I want to retrieve a reservation to cancel it. be informed that my reservation was purchased using one transitional award and complete the cancel process

Scenario: Anonymous User cancels a reservation purchased with one transitional award
Given I am a Rapid Rewards Member passenger with freedom awards
And I have the following itinerary data:

    |Field|Value|
    |itineraryType|One Way|
    |departureStation|HOU|
    |departureDate|+4|
    |arrivalStation|DAL|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|FreedomAward|

And I have booked this flight using 1 freedom award(s)
When I retrieve the Air reservation to cancel it
Then I see the Southwest Airlines - Cancel Air Reservation page
And I see the information of the 1 award(s) to be returned
When I confirm the air cancellation
Then I see the Cancellation Confirmation Page
And I see a message which informs that 1 award(s) will be returned to the corresponding rapid rewards account