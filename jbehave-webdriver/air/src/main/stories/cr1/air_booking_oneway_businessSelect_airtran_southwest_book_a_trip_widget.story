Search from book a trip widget and
Verify Business Select Available on Bug Page

Meta:
@project cr1
@airTranOnly
@flow air
@process booking
@traveler adult
@storyId DCQA161,ZR-976
@dyna_stubs
@project_in_dev
@not_passing flaky test - Jira ZR-1457

Narrative:
In order to book a flight using Business Select fare type from the book a trip widget
As a customer
I want to verify AirTran\SouthWest itinerary displayed on confirmation page


Scenario: View business select on bug page

Given I am flying a BusinessSelect one-way FLWNCodeShare flight
And I am logged in as Rapid Rewards member on the Rapid Rewards Account page
When I select book a flight
And I am searching for one-way flights from the book a flight widget
And I select and purchase a flight
Then I receive an air confirmation number