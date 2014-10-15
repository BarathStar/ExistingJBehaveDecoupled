RR sign up module should not present on the confirmation page when check box is not selected from purchase page

Meta:
@flow rr
@process loyalty
@user anonymous
@traveler adult
@passenger adult
@dyna_stubs
@project avengers_13.7
@project_in_dev

Narrative:
As an anonymous user in Southwest.com
I want to verify RR sign up module should not present on the confirmation page
when I do not select RR sign up check box from purchase page

Scenario: RR sign up module should not present on the confirmation page when check box is not selected from purchase page.
Given I am traveling as a:
    |Field|Value|
    |adultPassengerCount|1|
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

And the RR Sign Up on Purchase Page and Confirmation Page toggle is ON
And the RR Sign Up on Confirmation Page toggle is OFF
When I am on the purchase page
And I view RR Signup module present in purchase page
And I fill in the Purchase form and submit
Then I verify RR Sign Up Module is not present in Confirmation Page
