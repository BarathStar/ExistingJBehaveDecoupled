Verify WNFL-FLWN roundtrip itinerary displayed on Price Page

Meta:
@project cr1
@airTranOnly
@flow air
@process Information Search
@user
@traveler adult
@storyId DCQA-43
@dyna_stubs


Narrative:  In order to verify itinerary displayed on pricing page
As a customer
I want to verify itinerary displayed on confirmation page

Scenario: Viewing a WNFL FLWN Round Trip Itinerary

Given I am flying a round-trip WNFLCodeShare WNFLCodeShare flight
And I am a customer searching for round-trip flights from the search flights page
When I select and view the Price page for a flight
Then I should see the correct pricing page without logo verify
And I see the SouthwestCodeshare-mixed specific travel guidelines
