Attempt to Apply Travel Funds using invalid PNR on Purchase Page

Meta:
@flow air
@process view
@user anonymous
@traveler anonymous
@not_passing draft

Narrative:
I see the appropriate Oops messages for an invalid PNR
As an adult passenger
I attempt to apply travel funds on the purchase page using an invalid PNR


Scenario:
Adult passenger booking a flight, currently on the  purchase page attempting
to apply travel funds using an invalid PNR after attempting to  (Scenario 10)

Given I am flying a round-trip Southwest Southwest flight
And I am on the purchase page
When I enter an invalid confirmation number as a travel fund
Then I view the OOPS message for the invalid PNR using travel funds
