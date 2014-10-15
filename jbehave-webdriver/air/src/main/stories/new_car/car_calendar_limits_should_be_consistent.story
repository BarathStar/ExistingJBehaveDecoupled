Verify Car Calendars Limits Are Equals Between Home And Search Pages

Meta:
@user anonymous
@traveler adult
@dyna_stubs
@storyId SWAT-1470
@homepage2off
Narrative:
In order find a car for my trip
As an adult
I want to verify that the calendar limits should be consistent between widget at home page and car search page

Scenario: verify if the limits of calendar are the same in home page and car search page
Given I am on the Home page
And I have clicked on the Car tab in widget
And I have reached the calendar date limit at car widget in the home page
When I navigate to the car reservation home page
And I reach the calendar date limit on the Car_Search page
Then I should have the same Calendar limit
