Verify all the mandatory fields related to the credit card must be entered in order to complete the reissue process successfully for a Rapid Reward Inactive Member.

Meta:
@flow rr
@process loyalty
@user rr_member
@traveler adult
@dyna_stubs
@storyId AV-2443
@project_in_dev
@project avengers_14.1

Narrative:
In order to extend the expiration date of a standard award available for reissue
As a Rapid Rewards Inactive User
I want to see all my standard awards available for reissue,
select one of them and indicate all the credit card information correctly to complete
the reissue process

Scenario:  Rapid Rewards Inactive Member extends the expiration date of a standard award available for reissue

Given I am a Rapid Rewards Member with reissuable Standard Awards with my account Inactive
When I login from Login page
And I select to see my old awards
Then I see my awards at the Certificates Page
When I select to extend the standard awards available for reissue
And I attempt to reissue one of my standard awards
Then I see the Reissue Pricing Page
When I attempt to complete the award reissue without entering the credit card information
Then I see an oops message stating that the credit card information was not completed
When I complete the credit card information
And I confirm to finish the purchase
Then I am confirmed that my award was reissued on the Reissue Awards Confirmation Page
