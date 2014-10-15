Enter stations using auto-complete on the Low fare calendar page.

Meta:
@project bookingWidget
@project coda
@flow air
@process booking
@traveler adult
@user sb_user
@dynastubs
@not_live

Narrative:  In order to enter stations using auto-complete
As a user
I want to see auto-complete functionality on low fare calendar
So that I can find stations easily

Scenario: Forward filtering. Verify valid Market Pairs

Given I have anonymously logged into a SWABiz account
And I have clicked on the Low Fare Calendar link
When I select MSY in the From field
And I select New in the To field
Then I should see New Orleans, LA - MSY in From field
And I should see New York (LaGuardia), NY - LGA in To field
When I select MDW in the From field
And I attempt to enter mex in the To field
Then I should see Chicago (Midway), IL - MDW in From field
And I should see the To field is empty
When I select Day in the From field
And I attempt to enter Atl in the To field
Then I should see Dayton, OH - DAY in From field
And I should see the To field is empty

Scenario: Verify backward filtering between To and From field

Given I have anonymously logged into a SWABiz account
And I have clicked on the Low Fare Calendar link
When I select MDW in the To field
And I select HOU in the From field
Then I should see Chicago (Midway), IL - MDW in To field
When To, From fields are empty
And I select ATL in the To field
And I select Day in the From field
Then I should see the To field is empty

Scenario: Verify backward filtering between Return and To field

Given I have anonymously logged into a SWABiz account
And I have clicked on the Low Fare Calendar link
When I want to return to a different city
And I select ATL in the Return field
And I select Day in the To field
Then I should see the Return field is empty
When From, Return, To fields are empty
And I select Chicago in the Return field
And I select HOU in the To field
Then I should see Chicago (Midway), IL - MDW in Return field
