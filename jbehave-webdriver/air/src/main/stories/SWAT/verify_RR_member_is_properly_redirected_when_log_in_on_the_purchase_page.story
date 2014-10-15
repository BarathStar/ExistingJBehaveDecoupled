Verify if, after getting into purchase page as an anonymous user, when login in as a RR member in the account bar, the purchase form is filled in with the data in my RR account

Meta:
@flow air
@process view
@user rr
@passenger adult
@project SWAT
@dyna_stubs
@not_live
@project_in_dev

Narrative:
As an adult
I want to book a flight with my RR account
So that I get into the purchase page and access to my RR account

Scenario:Verify that the purchase form is filled in after login in RR

Given I have the following itinerary data:
    |Field|Value|
    |itineraryType|One Way|
    |departureStation|DAL|
    |arrivalStation|HOU|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Anytime|

And I am on the purchase page
And I am a Rapid Rewards Member passenger
When I login as an RR member
Then I see the passenger information correctly filled in on the Purchase page