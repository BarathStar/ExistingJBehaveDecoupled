Attempt to book an FL-WN open-jaw flight with less then 25 minutes layover time

Meta:
@flow air
@process booking
@user anonymous
@storyId DCAIR-7255
@not_passing code_defect_min_conn_time_FL
@dyna_stubs



Narrative:In order to determine minimum connection time
As an anonymous user
I want to select a  BOS-BWI-PDX open-jaw flight that has less then 25 minutes layover time
so that I see an OOPS message specifying minimum connection time at BWI station


Given I am flying an open-jaw AirTran SouthwestCodeshare flight
Given I am departing 9 days from now and returning 9 days from now
And I am on the Search Flight page
When I try to book an OpenJaw flight with less than 25 minutes layover
Then I see an Oops message The itinerary selected does not allow enough time to make your connection in Baltimore. The minimum connection time in Baltimore is 40 minutes
