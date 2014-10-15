unsubscribe from "Rapid Rewards Report" more than once, and verify a message informing me that I have already unsubscribed my e-mail the first time.

Meta:
@flow rr
@process loyalty
@user rr_member
@traveler adult
@dyna_stubs
@not_live
@project Loyalty Email_Login
@storyId SWACOMTT-1443
@user rapid rewards member

Narrative:
In order to unsubscribe from The Rapid Rewards Report mailing list twice
As a Rapid Rewards Member
I want to attempt to unsubscribe my e-mail from the mailing list twice and be informed that it is already unsubscribed

Scenario: RR Members unsubscribes from The Rapid Rewards Report more than once
Given I am a Rapid Rewards Member not logged-in located on the Rapid Rewards E-Mail page
When I enter my Rapid Rewards credentials on the Rapid Rewards E-Mail Page
And I subscribe to Rapid Rewards Report by entering the e-mail address rrrptwice@wnco.com
And I confirm to unsubscribe from the mailing list
Then I see a message which informs me that my e-mail was removed from the Rapid Rewards Report mailing list
When I confirm to unsubscribe from the mailing list again
Then I see a message which informs me that my e-mail is not included in the Rapid Rewards Report mailing list