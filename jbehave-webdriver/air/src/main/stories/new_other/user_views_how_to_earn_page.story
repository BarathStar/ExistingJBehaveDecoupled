View "How to Earn" page, so that I know how to earn Rapid Rewards points.

Meta:
@flow other
@user anonymous
@dyna_stubs

Narrative:
In order to know how to earn points
As an Anonymous User
I want to see "How to Earn" page

Scenario: User views "How to Earn" page
Given I am a Southwest User at Rapid Rewards Overview page
When I access How to Earn page
Then I see the Earn With Our Partners breadcrumb link on the How to Earn page
And I see the A-List link on the Earn When You Fly section is not selected
And I see the A-List Preferred link on the Earn When You Fly section is not selected
And I see Earn Points When You Fly is selected
When I select A-List on the Earn When You Fly section
Then I see the Earn With Our Partners breadcrumb link on the How to Earn page
And I see the Base link on the Earn When You Fly section is not selected
And I see the A-List Preferred link on the Earn When You Fly section is not selected
And I see Earn Points When You Fly is selected
When I select A-List Preferred on the Earn When You Fly section
Then I see the Earn With Our Partners breadcrumb link on the How to Earn page
And I see the Base link on the Earn When You Fly section is not selected
And I see the A-List link on the Earn When You Fly section is not selected
And I see Earn Points When You Fly is selected