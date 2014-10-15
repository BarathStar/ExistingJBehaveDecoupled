Verify I can choose a city from drop-down list on Rapid Rewards Partners Cars Page as an user

Meta:
@flow car
@process view
@user anonymous
@passenger adult
@dyna_stubs
@not_live

Narrative:
As an user
I want to be able to choose any city on the drop-down list location when I try to rent a car that is a Rapid Reward Partner
So that

Scenario: Verify I can choose any city on drop-down list location

Given I am on the Homepage
When I click on Rapid Rewards Partners
And I click on Rapid Rewards Partners Category named Travel
And I click on Rapid Rewards Partners named Rental Car
And I select Hertz for Vendor Car
And I enter pickup Location Cit
And I choose any city with Cit on the drop down list
Then I verify that what I chosen it is selected