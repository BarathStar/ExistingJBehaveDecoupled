Display Oops message on select flights page when I try to book a round-trip without the right promotion certificates

Meta:
@flow rr
@process loyalty
@user rr_member
@traveler adult
@dyna_stubs
@not_live
@storyId BUG-3762

Narrative:
As a RapidRewards Member
When I have multiple promotional certificates with earlier certs active and latter certs used, and I try to book a promo cert round-trip pnr with travel dates after the last unused cert,
Then I see an Oops message that the outbound date I selected is unavailable for the certificate

Scenario: RR Member views promotions, tries to book a pnr with travel dates after the last unused cert, sees Oops message on select flights page
Given air itinerary data as follows:
    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|DAL|
    |arrivalStation|HOU|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|StandardAward|
    |departureMonth|+2/15|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|StandardAward|
    |returnMonth|+2/16|
    |adultPassengerCount|1|
And I am a Rapid Rewards Member with X and V promotional certificates A63000,A64000,A65000,A66000,A67000,A68000,A69000,A70000 with expiration dates +1/14,+1/14,+2/14,+2/14,+3/14,+3/14,+4/14,+4/14 and certificateStatuses AVAILABLE,AVAILABLE,AVAILABLE,AVAILABLE,USED,USED,USED,USED in my account
When I log in from the account sidebar at the Search Flight page
And I expand My Rapid Rewards section
Then I see my promotion with its expiration date
When I request to see my promotions from the account bar
Then I view my active promotions in my account
When I search flights using a promotional certificate
Then I should see flights available in the select flight page in promotional certificate column
When I select flights and continue
Then I see an Oops messages from select flight page indicating that The outbound date you selected is unavailable with this certificate. Please select another date.
