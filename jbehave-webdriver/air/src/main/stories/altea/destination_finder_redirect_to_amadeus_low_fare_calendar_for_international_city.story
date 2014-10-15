Check dates button in destination finder should redirect to amadeus low fare calender for international city

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
I want to have international cities available on the Destination Finder map page
so that I can view Altea low fare calender when check dates button is clicked

Scenario:
Given I am logged in as Rapid Rewards member
And The following routes are available:
    |Field|Value|
    |originStation|ATL|
    |destinationStation|SJU|
    |carrierDates|WN:F:[today,today+90]
And The following routes are available:
    |Field|Value|
    |originStation|SJU|
    |destinationStation|ATL|
    |carrierDates|WN:F:[today,today+90]
When I am on the Destination Finder Page
And I select ATL as departing city
And I do a mouseover action on SJU city
And I click on Check Dates option
Then I see the Altea Low Fare Calendar for ATL - SJU