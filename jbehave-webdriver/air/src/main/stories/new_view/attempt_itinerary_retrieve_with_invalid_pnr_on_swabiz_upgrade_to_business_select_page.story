Attempt to retrieve Itinerary using invalid PNR on the SWABIZ Upgrade to Business Select Page

Meta:
@flow air
@process view
@user anonymous
@traveler anonymous
@not_passing draft

Narrative: In order to see the OOPS message
As a user
I attempt to retrieve a invalid Itinerary from the SWABIZ Upgrade to Business Select


Scenario: Attempt to retrieve an Invalid PNR from SWABIZ Upgrade to Business Select Page (Scenario 7)

Given I am on the SWABIZ Business Select Upgrade Page
When I attempt to retrieve an itinerary using an invalid PNR on the SWABIZ Business Select Upgrade page
Then I view the OOPS message for the invalid PNR on the SWABIZ Business Select Upgrade page
