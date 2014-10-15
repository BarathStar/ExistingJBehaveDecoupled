Ensure origin and destination fields on the home page are required

Meta:
@suite regression
@flow other
@not_live
@homepage2off

Narrative:
In order to verify the origin and destination fields on the home page are required
As an Anonymous user
I want to see Oops messages indicating the fields have not been entered

Scenario: Air form submission with missing fields

Given I am on the Home page
When I search for a flight from Home Page without departure and arrival
Then I see an Oops message No departure airport selected for the outbound flight
Then I see an Oops message No arrival airport selected for the inbound flight