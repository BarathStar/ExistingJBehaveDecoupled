View my Car reservation on Trip Details page so that I view all its detailed information even though it does not belong to a named trip.

Meta:
@flow rr
@process loyalty
@user rr_member
@not_live
@dyna_stubs

Narrative:
In order to view all the details of a Car reservation in Trip Details page
As a Rapid Rewards Member (with Upcoming Trips)
I want to check my Car reservation information in Trip Details page even though it does not belong to a named trip

Scenario: Rapid Rewards Member views the details of a Car reservation (which is not part of a trip) in "Trip Details" page

Given I am a Rapid Rewards Member passenger
And I have the following car itinerary data:
    |Field|Value|
    |pickUpStation|DAL|
    |dropOffStation|DAL|
    |pickUpDate|+1|
    |dropOffDate|+4|
    |carType|Compact|
    |carVendor|Budget|

And I have at least one Upcoming Trip in my account with a Car product which is not part of a trip
When I login from Login page
And I decide to view all the upcoming trips from account's Snapshot
And I select a Car reservation which is not part of a trip from Upcoming Trips page
Then I see on the Trip Details page that the Car reservation has no trip name provided by the user
And I see the details of the Car reservation on the Trip Details page