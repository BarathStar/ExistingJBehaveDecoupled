View "Earn with Partners" and "Promotions" pages

Meta:
@flow other
@user anonymous
@dyna_stubs

Narrative:
In order to know how to earn points throughout Southwest partners and see all the promotions available
As an Anonymous User
I want to see "Earn with Partners" and "Promotions" pages

Scenario: User views "Earn with Partners" and "Promotions" pages
Given I am a Southwest User at Rapid Rewards Overview page
When I access How to Earn page
And I access Earn With Partners page
Then I see the Earn Points When You Fly breadcrumb link on the Earn with Partners page
And I see Earn With Our Partners is selected
And I should not see the Base link
And I should not see the A-List link
And I should not see the A-List Preferred link
When I access Promotions page
Then I see the Promotions breadcrumb link on the Promotions page
And I see the Rapid Rewards Promotions section on the Promotions page