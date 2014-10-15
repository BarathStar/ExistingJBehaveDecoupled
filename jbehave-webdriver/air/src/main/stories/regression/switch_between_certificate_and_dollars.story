Switch between certificate and dollars

Meta:
@suite regression
@flow air
@process reservation
@user anonymous
@traveler adult
@not_passing draft
!-- css/xpath lookups too slow for test to succeed these need to be re-evaluated
!--Timeout - Element 'By.xpath: //div[@class='promoCertButtonContainer']/a' not found in 5 seconds
Narrative:
In order to better utilize award certificates
As a customer booking a flight
I want to be able to weigh my options between spending dollars or using a certificate

Scenario: Start booking a flight using an award certificate, switch to dollars, and then switch back to certificate

Given I am flying a one-way Southwest flight
And I am logged in as a valid Rapid Rewards member on the Search Flights page
When I view my award certificates
And I search flights using a promotional certificate
And I change my search to dollars
And I change my search to certificate
Then I see the Certificate radio button selected to redeem a promotion