Validate the MBP Options page user selection when the user selects a MBP delivery option only for multisegments and it is redirected
to the MBP confirmation Page.

Meta:
@flow air
@process checkin
@user anonymous
@traveler adult
@project mbp
@dyna_stubs
@not_live

Narrative:
In order to see a confirmation message that my BP was sent to my mobile decive
As an anonymous adult which selects a MBP delivery option
I want to check in online with a MBP eligible PNR which has more than one segment

Scenario: When the PNR is MBP eligible and it has multiple segments and the user choose any MBP delivery option without
the print option selected the user see a confirmation message in the MBP Confirmation Page

Given air itinerary data as follows:
    |Field|Value|
    |itineraryType|One Way|
    |departureStation|AUS|
    |arrivalStation|DAL|
    |departingFlight_carrierCode|WN|
    |isValidForCheckin|true|
    |departingFlight_fareClass|WannaGetAway|
    |outboundRouting|Change Planes|
    |outboundConnectingStation|HOU|

And I have a flight booked
And I perform a checkin online
And I am in the MBP Options Page
And I check the MBP Text delivery option only
And I enter a valid US phone number in the Text field of the MBP Options page
And The NCR response is Success

When I click on the Continue button

Then I see the MBP Confirmation Page