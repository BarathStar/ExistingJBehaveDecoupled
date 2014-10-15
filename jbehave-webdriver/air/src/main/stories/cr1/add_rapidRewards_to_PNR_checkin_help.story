Add Rapid Rewards number to PNR checkin, click help link

Meta:
@project cr1
@flow air
@process booking
@traveler adult
@storyId DCQA52
@dyna_stubs
@project_AccordionPage

Narrative:
So that I can recieve help adding my RR number
As a user attempting to add an invalid RR number on the checkin page
I want to see a help pop-up box


Scenario: Add rapid rewards to PNR in checkin page with an invalid RR number and ask for help

Given I am traveling as a:

    |Field|Value|
    |adultPassengerCount|1|
    |seniorPassengerCount|0|

And I have the following itinerary data:

    |Field|Value|
    |itineraryType|One Way|
    |departureStation|DAL|
    |arrivalStation|AUS|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Anytime|
    |outboundRouting|Nonstop|

When I book a flight eligible for checkin 1 adult
Then I should see the confirmation page
When I click on the Check in button on the Confirmation Page
And I enter an invalid Rapid Rewards number on the Check In page
Then I see the invalid Rapid Rewards number Oops message
When I click the help link
Then I see the help popup box
