qVerify that in  Multi passenger itinerary change as an adult, the travel funds are applied correctly


Meta:
@flow air
@process change
@user anonymous
@passenger adult
@not_passing draft
@project SWAT
@dyna_stubs

Narrative:
As an adult
In order to change  Multi passenger itinerary
I want to verify that travel funds were applied in proper manner
So that

Scenario: Verify that Multi passenger itinerary change should applied travel funds correctly even when passenger's middle name is the same than a suffix


Given I have adult passengers data as follows:
     |firstName|middleName|lastName|suffix|
     |Jose|I|Primero|I|
     |Juan|II|Segundo|II|
     |Pable|II|Otro|II|

Given air itinerary data as follows:

     |Field|Value|
     |itineraryType|One Way|
     |departureStation|LAS|
     |arrivalStation|SMF|
     |departingFlight_carrierCode|WN|
     |departingFlight_fareClass|Anytime|
     |outboundRouting|Nonstop|

When I book a flight
Then I receive an air confirmation number
When I select to change my entire itinerary from the confirmation page
And I continue to the select flight page
And I upgrade to BusinessSelect fare and reach the reconcile page
Then I should see the travel funds that has been applied in a correct manner