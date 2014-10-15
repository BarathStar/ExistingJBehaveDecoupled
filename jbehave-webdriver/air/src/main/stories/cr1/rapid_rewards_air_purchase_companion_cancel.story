Purchase A Rapid Rewards Air Ticket with a Certain Account with Available Companion Tickets Privileges and Cancel the ticket

Meta:
@project cr1
@airTranOnly
@flow air
@process booking
@user rr_member
@traveler companion
@storyId DCQA-63, ZR-894, ZR-988
@dyna_stubs
@project_in_dev

Narrative:
As a customer logged into R.R account
I want to book a roundtrip Southwestcodeshare flight and add a companion to the member and then cancel the companion PNR
So that I view cancellation confirmation for companion PNR.

Scenario: Rapid Rewards Member air purchase a trip, add a companion ticket and cancel the companion PNR.

Given I have the following itinerary data:
    |Field|Value|
    |itineraryType|One Way|
    |departureStation|BWI|
    |departureDate|+3|
    |arrivalStation|FLL|
    |departingFlight_carrierCode|FL|
    |departingFlight_fareClass|Anytime|
    |outboundRouting|Nonstop|
And I am a Rapid Rewards Member with a designated companion and an upcoming trip from a given itinerary
And I login from Login page
When I add a companion ticket to my trip
And I continue the companion pricing
And I make a companion reservation
And I cancel the companion reservation
Then I view the flight cancellation confirmation
