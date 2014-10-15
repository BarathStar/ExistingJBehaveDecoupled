View the future flights from My Account Snapshot section

Meta:
@flow air
@process view
@user rr_member
@traveler adult
@dyna_stubs
@not_live

Narrative:
In order to see an error message when Upcoming Trips cannot be retrieved
As a Rapid Rewards Member
I want to log in and check my Upcoming Trips (when the service is unavailable) accessing through "My Account Snapshot" section

Scenario: Rapid Rewards Member views an error message when Upcoming Trips can not be retrieved due to service unavailability accessing through "My Account Snapshot" section
Given The service that retrieves the Upcoming Trips is unavailable
And I am a Rapid Rewards Member passenger
And I have selected the following itinerary data:
    |Field|Value|
    |itineraryType|One Way|
    |departureStation|HOU|
    |departureDate|+1|
    |arrivalStation|DAL|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Anytime|
    |outboundRouting|Nonstop|

And I have included the Air product in my shopping cart
And I have finished my purchase
When I log in from the account sidebar at the Search Flight page
And I click my account link
Then I see the My Account Snapshot page
And I see a message specifying that my upcoming trips cannot be retrieved on My Account Snapshot page