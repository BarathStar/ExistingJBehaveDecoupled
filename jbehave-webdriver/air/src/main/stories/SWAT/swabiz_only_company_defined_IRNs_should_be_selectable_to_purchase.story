Verify that only company-defined IRNs are selectable in the purchase page when that option is chosen by the CTM

Meta:
@flow rr
@process IRNs
@user customer
@passenger adult
@dyna_stubs
@not_live
@project_in_dev
@project SWAT
@story_id SWAT-2824

Narrative:
As a Traveler
In order to check available IRNs
I want to reach the flight purchase page
So that I verify that only company-defined IRNs are selectable

Scenario: Verify that only company-defined IRNs are selectable to purchase when that option is chosen by the CTM

Given All travelers must select from Company-Defined IRNs
And I am a SWABIZ Traveler located in the Traveler Account Login
And I have my RR account setup with an associated company ID
And I login into SWABIZ by entering my company ID, my account number and password
And I am flying a round-trip Southwest Southwest flight
And I search for a flight
And I select flights and continue
When I click continue to the Purchase page
Then I should see only company-defined IRNs availables to select