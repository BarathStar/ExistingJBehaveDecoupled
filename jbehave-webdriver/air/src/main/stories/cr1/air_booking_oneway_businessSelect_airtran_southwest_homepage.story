Verify AirTran/SouthWest RT Business Select itinerary displayed on Confirmation Page

Meta:
@project cr1
@airTranOnly
@flow air
@process booking
@traveler adult
@storyId DCQA161, DCQA182, ZR-978
@dyna_stubs
@project_in_dev

Narrative:
In order to confirm my booking for a mixed Business Select itinerary and consider related contextual offerings
As a customer booking a mixed AirTran/Southwest itinerary
I want to see my mixed itinerary displayed on confirmation page

Scenario: View business select on bug page

Given I am flying a BusinessSelect one-way FLWNCodeShare flight
When I am a customer searching for one-way flights from the home page
And I select and purchase a flight
Then I receive an air confirmation number
