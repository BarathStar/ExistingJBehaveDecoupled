Verify Senior Fares and Availability

Meta:
@project cr1
@flow air
@airTranOnly
@process booking
@traveler senior
@user anonymous
@storyId DCQA29 ZR-895
@not_passing draft

Narrative:
As a senior
I want to see the Select Flights page
So that I can verify Senior Fares and points toggle on Select Flights page


Scenario: Senior air search from Home Page

Given I am flying a one-way Southwest flight
When I search for flights as a senior from the Home page
Then I see the senior fares displayed
And I do not see the points toggle
