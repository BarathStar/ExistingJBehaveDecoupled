Verify selected stations are prepopulated on home page after cancelling in AirTran Redirect Modal

Meta:
@project cr1
@flow air
@process booking
@traveler adult
@storyId DCAIR6963
@project webreferral
@removedFromAirbooking
@dyna_stubs

Narrative:
In order to show selected stations are prepopulated on home page after cancel in AirTran Redirect Model
As a Customer
I want to select stations that are serviced by AirTran and be redirected to AirTran model
and cancel the AirTran Redirect Model should prepopulate the selected Stations on home page.


Scenario: Prepopulating the selected Stations on home page after cancelling in AirTran Redirect Model

Given I am on the Homepage
When I select a one-way Southwest trip from ATL to AUA on the home page
Then view the AirTran Redirect Modal with WCM Content
When I click cancel on the AirTran Redirect Modal
Then I should stay on the home page
And the station selections on home page are prepopulated
