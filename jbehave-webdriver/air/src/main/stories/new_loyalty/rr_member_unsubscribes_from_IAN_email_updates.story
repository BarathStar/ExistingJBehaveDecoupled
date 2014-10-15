Verify the subscription under my "Communication Preferences" when I check "My Preferences" is not shown

Meta:
@flow rr
@process loyalty
@user rr_member
@traveler adult
@dyna_stubs

Narrative:
As a Rapid Rewards Member
I want to click on the Unsubscribe link located in my e-mail subscription and not be shown my e-mail on the
In a Nutshell Section within my account
In order to unsubscribe from In a Nutshell E-Mail updates

Scenario: RR Members unsubscribes from In a Nutshell E-Mail updates and his e-mail is not displayed on his account
Given I am a Rapid Rewards Member not logged-in located in the page In A Nutshell Email Updates
When I subscribe to In a Nutshell using an address email and the data from my account
Then I see a message which confirms my In a Nutshell subscription
When I confirm to unsubscribe from the mailing list
Then I see a message which informs me that my e-mail was removed from the In a Nutshell mailing list
When I login as a Rapid Rewards Member
And I select the preference page
And I select the Communication Preferences link
Then I should not see my e-mail subscribed to In a Nutshell Email Updates