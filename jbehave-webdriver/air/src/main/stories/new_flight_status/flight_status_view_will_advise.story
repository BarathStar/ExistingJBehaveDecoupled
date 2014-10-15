
Verify that Southwest.com user sees flights that are in "Will Advise" status on Check Flight Status Page

Meta:

@flow air
@process flight_status
@user anonymous
@traveler adult
@dyna_stubs
@not_live

Narrative:

As a Southwest.com user checking the Flight Status
I want to recognize the flights that are in "Will Advise" status
So that I can see Estimated on the bottom row of flight # details row in the Departure and/or Arrival Time columns

Scenario:

Validate that Estimated is displayed inside flight # details row in the Departure and/or Arrival Time columns
on Check Flight Status Page.

Given I am on the Check Flight Status Results Page
When I check flight status details for a flight with WILL ADVISE status
Then I should see Estimated: TBD inside flight # details row in the Departure or Arrival Time columns
