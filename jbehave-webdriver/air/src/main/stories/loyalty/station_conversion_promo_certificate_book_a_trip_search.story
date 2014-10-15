Error message displayed when AirTran station discontinued using promo certs


Meta:
@promo_cert
@flow booking
@process view
@user rr_member
@project Loyalty Awards
@loyalty
@suite regression
@message special case can not be dyna stub
@project webreferral



Narrative:
In order to view error message when AirTran station has been discontinued
As a Customer
I want to see an error message indicating AirTran route currently unavailable

Scenario: RR Member views error message when searching for discontinued AirTran station

Given I am logged in as a promoUser Rapid Rewards member on the Search Flights page
When I expand My Rapid Rewards section
And I request to see my promotions from the account bar
And I attempt to search flights using a promotional certificate
And I select SEA in the From field
And I select CAK in the To field
And I click on the modal search button
Then I see an Oops messages from plan trip page indicating that Published scheduled service between (Seattle - SEA) and (Akron - CAK) does not begin until 08/19/2012
And I see the PlanTrip Page homie