Subscribe and unsubscribe from "Click 'N Save® Email Updates", so that I do not see the subscription under my "Communication Preferences" when I check "My Preferences".

Meta:
@flow rr
@process loyalty
@user rr_member
@traveler adult
@dyna_stubs

Narrative:
In order to unsubscribe from the Click 'N Save® E-Mail updates
As a Rapid Rewards Member
I want to click on the Unsubscribe link located in my e-mail subscription and not be shown my e-mail on
the Click 'n Save Section within my account

Scenario: RR Members unsubscribes from the Click 'N Save® E-Mail updates and his e-mail is not displayed on his account
Given I am a Rapid Rewards Member not logged-in located in the page Click 'N Save Email Updates
When I subscribe to Click 'N Save using an address email and the data from my account
Then I see a message which confirms my Click 'N Save subscription
When I confirm to unsubscribe from the mailing list
Then I see a message which informs me that my e-mail was removed from the Click 'N Save mailing list
When I login as a Rapid Rewards Member
And I select the preference page
And I select the Communication Preferences link
Then I should not see my e-mail subscribed to the Click 'N Save E-mail Updates