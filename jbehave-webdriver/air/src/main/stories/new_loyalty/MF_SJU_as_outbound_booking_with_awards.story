Book Flight from SJU using Awards in dotcom with javascript enabled for Rapid Rewards Members

Meta:
@flow rr
@process booking
@user rr_member
@traveler adult
@project smurfs_5.21
@not_live
@dyna_stubs
@not_passing draft

Narrative:
As a Rapid Rewards Member, I want to be able to book a flight from San Juan, PR using any kind of my awards,
so that I can ensure proper use of the website.

Scenario: Rapid Rewards Member air purchase selecting SJU as outbound with Awards.

Given I log in as a Rapid Rewards Member with both standard and freedom awards
And I have the following itinerary data:
    |Field|Value|
    |itineraryType|One Way|
    |departureStation|SJU|
    |arrivalStation|FLL|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|StandardAward|

And I am on the Certificates Page
And I attempt to book an awards flight
When I select flights and finish booking using an award
Then I should see the confirmation page