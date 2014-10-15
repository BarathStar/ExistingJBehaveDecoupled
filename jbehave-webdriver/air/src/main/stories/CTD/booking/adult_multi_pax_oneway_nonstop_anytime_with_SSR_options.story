Initial Purchase - Adult Multi-Pax OneWay Nonstop Anytime with SSR options

Meta:
@suite regression
@project ctd
@flow air
@process booking
@traveler adult
@storyId SWACOMTT974
@project_AccordionPage

Narrative: In order to verify user can book an itinerary
As a user
I want to book an itinerary for Adult Multi-Pax OneWay Nonstop Anytime with SSR options

Scenario: Book an itinerary for Adult Multi-Pax OneWay Nonstop Anytime with SSR options

Given air itinerary data as follows:

    |Field|Value|
    |itineraryType|One Way|
    |departureStation|HOU|
    |arrivalStation|DAL|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Anytime|
    |outboundRouting|Nonstop|
    |adultPassengerCount|8|

And I am on the purchase page
When I add SSR Options as follows:

  |Pax Index|Options|
  |0|spillableBatteries=1,canWalkButNeedAssistance,vision,peanutAllergy,cognitive|
  |1|nonSpillableBatteries=1,cannotWalk,peanutAllergy,hearing,cognitive|
  |2|vision,manualWheelchair,cognitive|
  |3|nonSpillableBatteries=2,canWalkButNeedAssistance,hearing,peanutAllergy,cognitive|
  |4|spillableBatteries=2,vision,hearing,assistanceAnimal,cognitive,cannotWalk|
  |5|nonSpillableBatteries=3,comfortCreature,peanutAllergy,cognitive|
  |6|spillableBatteries=3,oxygenConcentrator,hearing,peanutAllergy,cognitive|
  |7|nonSpillableBatteries=4,nowheelchairAssistanceType,vision,peanutAllergy,cognitive|

When I fill in the Purchase form and submit
Then I receive an air confirmation number



