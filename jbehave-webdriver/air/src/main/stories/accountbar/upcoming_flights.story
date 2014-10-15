User views next Flight within Upcoming Trips section in "Manage Travel/My Travel" Drawer

Meta:
@project GlobalAccountBar
@process all
@flow all
@user anonymous
@not_passing draft

Narrative:
As a logged-in user,
I want to see my next flight details in Upcoming Trips section
So that I can easily be aware of my upcoming flights from Manage Travel drawer.

Given I am on the Home page
And I am an anonymous user
When I look at the drawer title
Then It should read "Manage Travel"

Given I am an anonymous user
When I view the Account Bar
Then I see the "Manage Travel" drawer
And I will see a WCM snippet with the following message "Don't have any upcoming trips? Plan your next getaway! <button>Start Now</button>
#
#Given I am NOT logged-in / I am an anonymous user
#When I click at Start Now button
#Then I will see the Air plan trip page
#
#Given I am logged-in with any user
#When I look at the drawer title
#Then It should read "My Travel" rather than "Manage Travel"
#
#Given I am looking at Account Bar
#When I see "My Travel" drawer in an expanded state
#Then I will notice that there is a section title called "Upcoming Trips" rather than "Upcoming Flights"
#
#Given I am logged in and I have upcoming flights available
#When I look at Upcoming Trips section on the "My Travel" drawer
#Then I will see my next flight being shown
#
#Given My next flight is being shown at Upcoming Trips section on "My Travel" drawer
#When I see the details of the flight being shown
#Then I will see the mini flight icon, Origin and Destination cities (whole text is a link), Departure date, confirmation number, Depart and Arrive time, Check In Online button (if departure within 24 hours)
#
#Given I have an Open-Jaw Air in Upcoming Trips section under "My Travel" section
#When I see the details of the flight being shown
#Then I will see Origin and Destination cities of origin and the 1st destination
#
#Given I have an Air product where departure is within 24 hours under Upcoming trips section in Account Bar
#When I click on Check-In Online button
#Then I will see the check-in online page opening in the same browser tab and the flight credentials will be passed through so the I do not need to re-enter them
#
#Given I have a flight which city names are long in the Upcoming Trips section under "My Travel" drawer
#When I see the Air product details
#Then I will see Origin and Destination cities are completely displayed wrapping text (The wrapping should occur so if any part of a city needs to wrap, the entire "City, ST" should wrap to the next line)
#
#Given I have any flight, Trip or non-Trip
#When I see the Upcoming Trips under "My Travel"
#Then I will see this flight listed along with its trip name or default trip name (if not part of a trip). Trip name will cut-off if wider than Account Bar width
#
#Given I have an upcoming Air product with a companion pax
#When I see the Upcoming Trips section under "My Travel"
#Then I will not see the companion pax confirmation number
#
#Given I am viewing the Account Bar when flights can not be retrieved
#When I see the "My Travel" drawer
#Then I will see the following error message "We are currently unable to retrieve your upcoming trips. Please try again later"
#
#Given I have no flights booked in my account
#When I see Upcoming Trips section under Manage Travel
#Then I will see the following message "There are no upcoming trips at this moment. <link>Book a flight now!<link>"
#
#Given I want to see all Upcoming trips available
#When I click on "View all" link right beside Upcoming Trips section title
#Then I will be anchored to Upcoming Trips page
#
#Given I want to see details of the flight being shown on Upcoming Trips when TM feature toggle is ON
#When I click on Origin and Destination cities (whole text is a link)
#Then I will be anchored to the respective flight on the Trip Details Page
#
#Given I want to see details of flight being shown on Upcoming Trips when TM feature toggle is OFF
#When I click on Origin and Destination cities (whole text is a link)
#Then I will be anchored to respective Product details page
#
#Given I am looking at Account Bar across southwest site while logged-in
#When I visit the <page> of the southwest site
#Then I will see the Upcoming Trips section under "My Travel" drawer being shown
#
#Given I am opening the "My Travel" drawer for the first time when logged-in
#When I click on expand button  to open the drawer
#Then I will see an interstitial while the app calls to return the next upcoming flight. Once retrieved it will be stored on my session
#
#Given Java script is turned off
#When I view the account bar
#Then I will notice that Manage Travel/My Travel drawer will be default expanded and +/- button won't be shown
#
#Given I am viewing details of Upcoming Trips section under "My Travel" drawer
#When I mouse over "View All" link
#Then I will see that there is an alt text "View All"
#
#Given I am viewing details of the flight being shown at Upcoming Trips section under "My Travel" drawer
#When I mouse over the "Check In Online" button
#Then I will see that there is an alt text "Check In Online"
#
#Given I have a flight within 24 hours
#When I click 'Check In Online' button and check in online function is down
#Then I will be taken to the Check In Online flow and shown the existing Maintenance Error
#
#Given SWA Admin has turned AB Consistency toggle off
#When I look at Upcoming Trips section under "My Travel" drawer
#Then The section should look like production today. See comp attached
#
#Given I am a SWABIZ CTM Proxy
#When I look at the Account Bar
#Then The Manage Travel/My Travel drawer should be suppressed.
