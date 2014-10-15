Verify message informing me that I have already unsubscribed my e-mail the first time.

Meta:
@flow rr
@process loyalty
@user rr_member
@traveler adult
@dyna_stubs

Narrative:
In order to unsubscribe from the Click 'N Save mailing list twice
As a Rapid Rewards Member
I want to attempt to unsubscribe my e-mail from the mailing list twice and be informed that it is already unsubscribed

Scenario: RR Members unsubscribes from the Click 'N Save E-Mail more than once

Given I am a Rapid Rewards Member not logged-in located in the page Click 'N Save Email Updates
When I subscribe to Click 'N Save using an address email and the data from my account
Then I see a message which confirms my Click 'N Save subscription
When I confirm to unsubscribe from the mailing list
Then I see a message which informs me that my e-mail was removed from the Click 'N Save mailing list
When I confirm to unsubscribe from the mailing list again
Then I see a message which informs me that my e-mail is not included in the Click 'N Save mailing list
