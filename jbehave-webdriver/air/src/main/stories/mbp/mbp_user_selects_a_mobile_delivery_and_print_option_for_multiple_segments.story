Validate the MBP Options page user selection when he user selects a MBP delivery options plus MBP print option and
it is redirected to the Printing Page.

Meta:
@flow air
@process checkin
@user anonymous
@traveler adult
@project mbp
@dyna_stubs
@not_live

Narrative:
In order to see my Boarding Passes
As an anonymous adult which selects a MBP delivery option and MBP print option
I want to check in online with a MBP eligible PNR which has more than one segment

Scenario: When the PNR is MBP eligible and it has multiple segments and the user choose print plus any MBP delivery
option the user see multiple boarding passes and the confirmation message

Given air itinerary data as follows:
   |Field|Value|
   |itineraryType|Open Jaw|
   |departureStation|DAL|
   |departingFlight_carrierCode|WN|
   |departingFlight_fareClass|WannaGetAway|
   |isValidForCheckin|true|
   |outboundRouting|1 stop No Plane Change|
   |arrivalStation|HOU|
   |returnStation|LAX|
   |arrivingFlight_carrierCode|WN|
   |arrivingFlight_fareClass|BusinessSelect|
   |inboundRouting|Nonstop|

And I have an OJ flight booked eligible for checkin
And I perform a checkin online
And I am in the MBP Options Page
And I check the MBP Print option
And I check the MBP Text delivery option
And I enter a valid text in the Text field of the MBP Options page
And The NCR response is Success

When I click on the Continue button

Then I view my boarding pass(es) with the MBP Delivery Confirmation Message