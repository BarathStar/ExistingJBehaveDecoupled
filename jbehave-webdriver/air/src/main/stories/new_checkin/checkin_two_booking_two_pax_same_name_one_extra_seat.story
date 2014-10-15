Verify oops message when checkin a booking with extra seat as an adult

Meta:
@flow air
@process checkin
@user anonymous
@passenger adult
@dyna_stubs
@not_live

Narrative:
As an user
In order to checkin
I want to see an oops message when I try to chekin a second booking with extra seat
So that

Scenario: Verify oops message when checkin a second booking with extra seat

Given I have adult passengers data as follows:
    |firstName|middleName|lastName|suffix|isPOS|
    |Jose|I|Primero|XS|true|
    |Juan|II|Segundo|II|false|

And I have the following itinerary data:

    |Field|Value|
    |itineraryType|One Way|
    |departureStation|HOU|
    |arrivalStation|DAL|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Anytime|
    |outboundRouting|Nonstop|
    |isValidForCheckin|true|

And I have a flight booked
And I am on the check-in page
When I retrieve my reservation to checkin
And I click checkin to create a boarding pass
And I click on the Continue button if I am in the MBP options page
Then I view 1 southwest security document
Then I view 1 southwest boarding pass
Given I have adult passengers data as follows:
     |firstName|middleName|lastName|suffix|isPOS|
     |Jose|XS|Primero|I|true|
And I have this flight booked from .com with crossReference
And I am on the Home page
When I click on check-in in global navigation
And I retrieve my reservation to checkin
And I click checkin to create a boarding pass
And I click on the Continue button if I am in the MBP options page
Then I view an oops message that says is unable to check-in using this confirmation number