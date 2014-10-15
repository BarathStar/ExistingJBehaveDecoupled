Validate the behavior when the user only selects a MBP delivery option and the NCR call fails
and ensure the user is redirected to the BP Printing Page and they see the notification message
text on the top of the page.

Meta:
@flow air
@process checkin
@user anonymous
@traveler adult
@project mbp
@dyna_stubs
@not_live

Narrative:
In order to see the BP Printing Page with the NCR Failure Message
As an anonymous adult which selects a MBP delivery option and does not select the print option
I want to check in online with a MBP eligible user

Scenario: When NCR call fails, the user is redirected to BP Printing Page and see failure message on top

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
And I check the MBP Text delivery option only
And I enter a valid US phone number in the Text field of the MBP Options page
And The NCR response is Failure

When I click on the Continue button

Then I view my boarding pass AND I see the Failure message on the top of the page