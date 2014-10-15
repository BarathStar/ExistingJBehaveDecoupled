Validate the MBP Options page user selection when he user selects the MBP print option only
and it is redirected to the Printing Page.

Meta:
@flow air
@process checkin
@user anonymous
@traveler adult
@project mbp
@dyna_stubs
@not_live

Narrative:
In order to see my Boarding Pass
As an anonymous adult which selects the MBP print option and does not selects any delivery option
I want to check in online with a MBP eligible user

Scenario: In the MBP Options Page the user selects a MBP print option only (does not selects any delivery option)

Given air itinerary data as follows:
    |Field|Value|
    |itineraryType|One Way|
    |departureStation|AUS|
    |arrivalStation|DAL|
    |departingFlight_carrierCode|WN|
    |isValidForCheckin|true|
    |departingFlight_fareClass|WannaGetAway|
    |outboundRouting|1 stop No Plane Change|

And I have a flight booked
And I perform a checkin online
And I am in the MBP Options Page
And I check the MBP Print option only

When I click on the Continue button

Then I view my boarding pass