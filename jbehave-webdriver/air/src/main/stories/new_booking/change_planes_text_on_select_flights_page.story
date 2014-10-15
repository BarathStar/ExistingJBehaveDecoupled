Verify flight information on select flights page

Meta:
@flow air
@process booking
@traveler adult
@not_passing draft
@owner Rachel Fixing to work with new ReservationSpecificationBuilder
@project dot

Narrative:
In order to verify important flight information
As an adult
I want to view change planes routing information

Scenario: itinerary changes planes
Given air itinerary data as follows:
    |Field|Value|
    |itineraryType|One Way|
    |departureStation|SFO|
    |outboundConnectingStation|DEN|
    |arrivalStation|BWI|
    |departingFlight_carrierCode|WN|
    |outbound_connection_carrierCode|WN|
    |departingFlight_fareClass|Anytime|
    |outboundRouting|Change Planes|

And I am on the select flights page
When I sort the outbound routing column in descending order
Then I should see that the outbound routing column is sorted in descending order
Then I should see the change planes text in the routing column
