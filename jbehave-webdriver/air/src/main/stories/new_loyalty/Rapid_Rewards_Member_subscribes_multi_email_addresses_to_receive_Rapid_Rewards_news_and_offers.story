Subscribe multi email addresses, so that I can receive Rapid Rewards news and offers from Rapid Rewards E-mail Update and The Rapid Rewards Report.

Meta:
@flow rr
@process loyalty
@user rr_member
@traveler adult
@dyna_stubs

Narrative:
In order to subscribe to the Rapid Rewards news and offers updates with multiple e-mails
As a Rapid Rewards Member
I want be shown my e-mail addresses as communication preferences on the Rapid Rewards Sections within my account

Scenario: RR Members subscribes to Rapid Rewards News and updates with multiple e-mails
Given I am a Rapid Rewards Member not logged-in located on the Rapid Rewards E-Mail page
When I enter my Rapid Rewards credentials on the Rapid Rewards E-Mail Page
And I subscribe to Rapid Rewards E-mail Update and The Rapid Rewards Report by entering the first email address test123@wnco.com
And I subscribe to The Rapid Rewards Report by entering my second email address test555@wnco.com
And I subscribe to Rapid Rewards E-mail Update by entering my third email address test666@wnco.com
Then I see my first e-mail subscribed to Rapid Rewards E-mail Update and The Rapid Rewards Report
And I see my second e-mail only subscribed to The Rapid Rewards Report
And I see my third e-mail only subscribed to Rapid Rewards E-mail Update
When I login as a Rapid Rewards Member
And I check my preferences in my account
And I select to see my communication preferences in my account
Then I see my first and second e-mail subscribed to the Rapid Rewards Report
And I see my first and third e-mail subscribed to the Rapid Rewards E-mail Update