View my Hotel reservation on Trip Details page so that I view all its detailed information even though it does not belong to a named trip.

Meta:
@flow rr
@process loyalty
@user rr_member
@not_live
@dyna_stubs

Narrative:
In order to view all the details of a Hotel reservation in Trip Details page
As a Rapid Rewards Member (with Upcoming Trips)
I want to check my Hotel reservation information in Trip Details page even though it does not belong to a named trip

Scenario: Rapid Rewards Member views the details of a Hotel reservation (which is not part of a trip) in "Trip Details" page
Given I am a Rapid Rewards Member passenger
And I have the following hotel itinerary data:
    |Field|Value|
    |destination|DAL|
    |hotelName|Marriot|
    |checkInDate|+1|
    |checkOutDate|+4|

And I have at least one Upcoming Trip in my account with a Hotel product which is not part of a trip
When I login from Login page
And I decide to view all the upcoming trips from account's Snapshot
And I select a Hotel reservation which is not part of a trip from Upcoming Trips page
Then I see on the Trip Details page that the Hotel reservation has no trip name provided by the user
And I see the details of the Hotel reservation on the Trip Details page