Add Rapid Rewards number to PNR checkin

Meta:
@project cr1
@flow air
@process booking
@traveler adult
@user anonymous
@dyna_stubs
@storyId DCQA-52, ZR-894
@project_in_dev

Narrative:
As a customer
I want to book a one-way southwest flight and I am on the checkin page
And I enter invalid rapid rewards number on the checkin page
So that I view the rapid rewards number oops message and when I click the help link I see the help popup box.

Scenario: Use invalid Rapid Rewards number when I checkin

Given I am flying a one-way Southwest flight
When I book a flight eligible for checkin 1 adult
Then I should see the confirmation page
When I click on the Check in button on the Confirmation Page
And I enter an invalid Rapid Rewards number on the Check In page
Then I see the invalid Rapid Rewards number Oops message
When I click the help link
Then I see the help popup box
