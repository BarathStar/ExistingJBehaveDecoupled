View my Upcoming Trips so that

Meta:
@flow air
@process booking
@user rr_member
@traveler adult
@project codaPostSell
@dyna_stubs
@not_passing draft
@not_live
@project_in_dev
@storyid OPS-1379

Narrative:
As a Rapid Rewards Member
I want to be able to add Early Bird Checkin from within My Rapid Rewards Account My Travel Trip Details page for Domestic Upcoming Trip
so that I can board the plane earlier than the other people

Scenario: Rapid Rewards Member accesses Earlybird purchase from Upcoming Trip Details page
Given I am a Rapid Rewards Member passenger
And I have the following itinerary data:
    |Field|Value|
    |itineraryType|One Way|
    |departureStation|HOU|
    |departureDate|+5|
    |arrivalStation|DAL|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Anytime|
    |outboundRouting|Nonstop|


And I have included an Air in my shopping cart
And I have finished my purchase adding my products to a new trip named My Trip
When I log in from the account sidebar at the Search Flights page
And I click my account link
And I select an Air reservation which is part of My Trip
And I attempt to purchase Early Bird on Trip Details page
Then I am taken to the EB direct purchase path
