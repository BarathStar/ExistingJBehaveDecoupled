Non-verified senior AirTran only check-in

Meta:
@flow air
@project cr1
@process checkin
@user anonymous
@traveler senior
@dyna_stubs
@project_in_dev
@storyId ZR-883

Narrative:
In order to verify a friendly OOPS message for attempted check in for an AirTran only PNR
As a non-verified senior
I want to see a user friendly OOPS message when I attempt online check-in

Scenario:  Display user friendly OOPS message for a non-verified senior AirTran only check in

Given air itinerary data as follows:
    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|FLL|
    |arrivalStation|BWI|
    |departingFlight_carrierCode|FL|
    |departingFlight_fareClass|Senior|
    |outboundRouting|0 stop No Plane Change|
    |arrivingFlight_carrierCode|FL|
    |arrivingFlight_fareClass|Senior|
    |inboundRouting|0 stop No Plane Change|
    |seniorPassengerCount|1|

And I book a flight eligible for checkin 1 senior
When I check in from the check in page
Then I see an Oops messages from check in page indicating that Your Itinerary is ineligible for check in online. If you have questions or require further assistance, please contact a Southwest Airlines Customer Representative at 1-800-I-FLY-SWA (1-800-435-9792).