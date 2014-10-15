Verify error message when check-in of code share itinerary for Non-age verified Senior for a One-way

Meta:
@project cr1
@airTranOnly
@flow air
@process checkin
@traveler adult
@user anonymous
@storyId DCQA-42, ZR-870
@dyna_stubs
@project_in_dev


Narrative:
As a non-age verified senior
I want to check-in for codeshare PNR
So that error message displays


Scenario: verify check-in for a WN_FL Itinerary

Given I am flying a one-way CodeShare flight with a 1 stop segment
When I book a flight eligible for checkin 1 senior
And I retrieve travel documents
And I click checkin to create a boarding pass
And I click on the Continue button if I am in the MBP options page
Then I should receive my security document

