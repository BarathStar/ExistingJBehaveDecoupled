[Multifleet]: Book a Flight including SJU as departure station in SWABIZ

Meta:

@flow air
@process booking
@user sb_anonymous
@traveler adult
@project smurfs_5.21
@removedFromAirbooking
@dyna_stubs
@not_live

Narrative:

As a SWABIZ.com Customer with JavaScript Enabled, I want to be able to book a flight includin San Juan, PR so that
I can ensure proper use of the website.

Scenario: Users are able to book flights with San Juan as departure station in Swabiz.

Given I am a SWABiz Anonymous user logged into a SWABiz account
When I attempt to book a travel selecting SJU as a departure station
Then I see the departure station description 'San Juan, PR - SJU'