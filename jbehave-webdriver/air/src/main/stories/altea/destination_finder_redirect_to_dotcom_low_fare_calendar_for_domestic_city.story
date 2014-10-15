Check dates button in destination finder should redirect to dotcom low fare calender for domestic city

Meta:
@flow rr
@process loyalty
@user rr_member
@dyna_stubs
@not_live
@project coda
@project_in_dev
@storyid DCAIR-7829

Narrative:
As a Rapid Rewards member user in southwest.com or swabiz.com
I want to have domestic cities available on the Destination Finder map page
so that I can view dotcom or swabiz low fare calender when check dates button is clicked

Scenario:
Given I am logged in as Rapid Rewards member
And The following routes are available:
    |Field|Value|
    |originStation|DAL|
    |destinationStation|HOU|
    |carrierDates|WN:T:[today,today+90]
And The following routes are available:
    |Field|Value|
    |originStation|HOU|
    |destinationStation|DAL|
    |carrierDates|WN:T:[today,today+90]
When I am on the Destination Finder Page
And I do a mouseover action on HOU city
And I click on Check Dates option
Then I view the Low Fare Calendar page