Subscribe and unsubscribe from the Rapid Rewards news and offers, so that I do not see the subscription under my "Communication Preferences" when I check "My Preferences".

Meta:
@flow rr
@process loyalty
@user rr_member
@traveler adult
@dyna_stubs

Narrative:
In order to unsubscribe from the Rapid Rewards news and offers updates
As a Rapid Rewards Member
I want to click on the Unsubscribe link located in my e-mail subscription and not be shown my e-mail as a communication preference on the Rapid Rewards Sections within my account

Scenario: RR Members unsubscribes from Rapid Rewards News and Offers and his e-mail is not displayed on his account
Given I am a Rapid Rewards Member not logged-in located on the Rapid Rewards E-Mail page
When I enter my Rapid Rewards credentials on the Rapid Rewards E-Mail Page
And I subscribe to Rapid Rewards E-mail Update and The Rapid Rewards Report by entering the email address rreurrrp@wnco.com
And I confirm to unsubscribe the email address rreurrrp@wnco.com from the Rapid Rewards Email Update mailing list
Then I see a message which informs me that my e-mail was removed from the Rapid Rewards Email Update mailing list
When I confirm to unsubscribe the email address rreurrrp@wnco.com from the Rapid Rewards Report mailing list
Then I see a message which informs me that my e-mail was removed from the Rapid Rewards Report mailing list
When I login as a Rapid Rewards Member
And I check my preferences in my account
And I select to see my communication preferences in my account
Then I should not see my e-mail subscribed to the Rapid Rewards Report and the Rapid Rewards E-mail Update