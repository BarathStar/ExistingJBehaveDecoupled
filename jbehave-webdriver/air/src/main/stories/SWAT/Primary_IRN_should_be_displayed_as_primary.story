Verify that changing the primary IRN is shown in purchase air page, when you traveling as an adult

Meta:
@flow rr
@process IRNs
@user customer
@passenger adult
@dyna_stubs
@not_live
@project_in_dev
@project SWAT
@storyId SWAT-2617

Narrative:
As a Traveler
In order to do a booking with another IRN
So that I want to change the primary IRN

Scenario: Verify that changing the primary IRN is shown in purchase page

Given I am a SWABIZ Traveler located in the Traveler Account Login
And I have my RR account setup with an associated company ID
When I login into SWABIZ by entering my company ID, my account number and password
And I select the link My Preferences
And I select the Payment Information link
And I select to see the edit mode for my IRN
And I choose another Primary IRN
And I logout from my Traveler Account
Given I have the following itinerary data:

          |Field|Value|
          |itineraryType|One Way|
          |departureStation|DAL|
          |arrivalStation|HOU|
          |departingFlight_carrierCode|WN|
          |arrivingFlight_carrierCode|WN|
          |departingFlight_fareClass|Anytime|

When I login into SWABIZ by entering my company ID, my account number and password
And I search for a flight
And I select flights and continue
And I click continue to the Purchase page
Then I see the primary IRN changed