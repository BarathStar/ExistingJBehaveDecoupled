Book a Flight for an Adult with a Disability Request

Meta:
@suite regression
@project ctd
@flow air
@process booking
@traveler adult
@project_AccordionPage

Narrative: In order to verify user can book an itinerary
As a user
I want to book an itinerary for an Adult with a Disability Request

Scenario:  Book a Flight for an Adult with a Disability Request

Given I am flying a round-trip Southwest Southwest flight
And I am on the purchase page
When I add SSR Options as follows:

    |Pax Index|Options|
    |0|spillableBatteries=4,vision,peanutAllergy,cognitive|

When I fill in the Purchase form and submit
Then I receive an air confirmation number
