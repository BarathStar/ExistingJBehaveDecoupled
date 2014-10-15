Verify that Online Check-in shows the Assistant Animals link and goes to correct URL.

Meta:
@flow air
@process checkin
@user anonymous
@traveler adult
@dyna_stubs
@not_live

Narrative:
As a southwest.com user
I want to be able to access Assistant Animals page from Online Check-in page
So that I can know more about Assistant Animals information.

Scenario: Any visitor to southwest.com selects Infant Flying link on the Global Nav bar to access Infant Flying information page.

Given I am traveling as a:
    |Field|Value|
    |adultPassengerCount|1|
    |seniorPassengerCount|0|

And I have the following itinerary data:
    |Field|Value|
    |itineraryType|One Way|
    |departureStation|CAK|
    |arrivalStation|HOU|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|BusinessSelect|
    |outboundRouting|Nonstop|
    |isValidForCheckin|true|

And I have the flight booked for a passenger named JANE DOE
And I am on the check-in page
And I retrieve my reservation to checkin
When I select Assistant Animals link under the Additional Documentation required section
Then I see the Customers with Disabilities page with Assistant Animals tab selected