Change a Wanna get way - web only fare round-trip booking to anytime as an Adult

Meta:
@flow air
@process change
@user anonymous
@passenger adult
@dyna_stubs
@not_live
@project_in_dev
@story_id SWAT-1461
@project SWAT

Narrative:
As an Adult
In order to do a second change in a booking
I want to be able to change a booking from Wanna get way - web only fare to Anytime fare
So that


Scenario: verify if I can do two changes from Wanna get Away- Web only fare to Anytime fare

Given I have the following itinerary data:
    |Field|Value|
    |adultPassengerCount|1|
    |seniorPassengerCount|0|
    |itineraryType|Round Trip|
    |departureStation|HOU|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|WannaGetAway|
    |outboundRouting|Nonstop|
    |arrivalStation|SMF|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|WannaGetAway|
    |inboundRouting|Nonstop|
    |departureDate|+12|
    |returnDate|+13|
    |isWebOnly|true|

And I have a flight booked
And I have the following itinerary data:
    |Field|Value|
    |itineraryType|One Way|
    |departureStation|SMF|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|WannaGetAway|
    |arrivalStation|HOU|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|WannaGetAway|
    |inboundRouting|Nonstop|
    |departureDate|+13|
    |isWebOnly|true|

And I retrieve my reservation for change
And I select the inbound flight to change to WannaGetAway webOnly fare
And I click continue to the Price page
And I click Continue to the Reconcile page
And I fill reconcile payment information and confirm
And I retrieve my reservation for change
And I select the inbound flight to change to Anytime not-webOnly fare
When I click continue to the Price page
Then I should see the Price Page with the new price