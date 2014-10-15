Verify the security document printed for a pax with extra seat and a boarding pass for a pax without ES as an adult

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
I want to see my security document and my boarding pass
So that

Scenario: Verify the security document printed for a pax with extra seat and a boarding pass for a pax without ES

Given I have adult passengers data as follows:

    |firstName|middleName|lastName|suffix|isPOS|
    |Jose|I|Primero|XS|true|
    |Juan|II|Segundo|II|false|
    |Jose|XS|Primero|XS|true|

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