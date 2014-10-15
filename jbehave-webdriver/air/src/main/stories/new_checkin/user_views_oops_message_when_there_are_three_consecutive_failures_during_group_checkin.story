Attempt a group checkin three consecutive timesfor ineligible and eligible passengers

Meta:
@flow air
@process checkin
@user anonymous
@traveler adult
@project group_checkin
@dyna_stubs
@not_live

Narrative:
In order to perform the group check-in for some passengers
As an adult travelling with 9 other adults
I want to attempt to complete the check-in for a group of passengers

Scenario: User views an Oops error message when there are three consecutive failures during group checkin process
Given I am:
    |Field|Value|
    |adultCheckInPassengerCount|10|
    |seniorPassengerCount|0|

And I have the following itinerary data:
    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|HOU|
    |departureDate|+1|
    |arrivalStation|DAL|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Anytime|
    |outboundRouting|Nonstop|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|Anytime|
    |inboundRouting|Nonstop|


And I have booked this group reservation with all its passengers eligible for checkin online
And I have 3 passengers with errors in their checkin
When I retrieve my reservation to checkin
And I select all the passengers available for checkin except for one
And I confirm to start the checkin process
Then I see an error message next to each passenger who failed to checkin
And I should not see any oops message since there were not three fails in a row
When I confirm to start the checkin process
Then I see an error message next to each passenger who failed to checkin
And I see an oops message since there were three fails in a row
When I select all the passengers available for checkin
And I confirm to start the checkin process
Then I should not see any oops message since there were not three fails in a row
And I see all the passengers without errors in their checkin under the Checkin Results section
And I see the passengers with errors available for checkin online
