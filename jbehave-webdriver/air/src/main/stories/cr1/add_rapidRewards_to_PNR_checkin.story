Add Rapid Rewards number to PNR checkin

Meta:
@project cr1
@flow air
@process checkin
@traveler adult
@storyId DCQA52
@dyna_stubs
@project_AccordionPage


Narrative:
So that I can utilize my RR benefits for my upcoming flight
As a RR member on the check-in page
I want to add/attach my RR number to my PNR

Scenario: Add rapid rewards to PNR in checkin page

Given I am traveling as a:

    |Field|Value|
    |adultPassengerCount|1|
    |seniorPassengerCount|0|

And I have the following itinerary data:

    |Field|Value|
    |itineraryType|One Way|
    |departureStation|DAL|
    |arrivalStation|HOU|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Anytime|
    |outboundRouting|Nonstop|

When I book a flight eligible for checkin 1 adult
Then I should see the confirmation page
When I click on the Check in button on the Confirmation Page
And I enter my Rapid Rewards number on the Check In page
Then the Rapid Rewards update message is displayed
