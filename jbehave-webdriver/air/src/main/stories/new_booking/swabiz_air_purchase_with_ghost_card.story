Purchase A Flight On Swabiz With A Ghost Card

Meta:
@flow air
@process booking
@user anonymous
@traveler adult
@dyna_stubs
@not_live
@not_passing broken OPS-1954

Narrative:
In order to fly on a date that I want
As a SWABIZ customer
I want to book a flight with a ghost card and receive a confirmation number

Scenario: book a flight with a ghost card
Given I have the following itinerary data:
    |Field|Value|
    |itineraryType|One Way|
    |departureStation|DAL|
    |arrivalStation|HOU|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Anytime|

And I am a SWABIZ Traveler located in the Traveler Account Login
And I have my RR account setup with an associated company ID
When I login into SWABIZ by entering my company ID, my account number and password
And I search for a flight
And I select flights and continue
And I click continue to the Purchase page
And I select a ghost card
And I continue to Confirmation page from Purchase page
Then I receive an air confirmation number