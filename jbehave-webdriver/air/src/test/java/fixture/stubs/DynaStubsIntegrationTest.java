package fixture.stubs;

import org.jbehave.core.model.ExamplesTable;
import org.joda.time.LocalDate;
import org.joda.time.Period;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static org.joda.time.DateTimeConstants.*;
import static org.mockito.Mockito.mock;

public class DynaStubsIntegrationTest {

    private static final LocalDate NOW = LocalDate.now();
    private static final String ORIGIN = "DAL";
    private static final String DESTINATION = "ATL";
    private static final int DAYS_TO_ADD = 11;
    private static final String DAYS_TO_ADD_STRING = new StringBuilder("+").append(DAYS_TO_ADD).append("d").toString();
    private static final LocalDate DAY_BEFORE_SPRING_DST = new LocalDate(2013, MARCH, 10).minusDays(1);

    private DynaStubsIntegration integration;
    private ExamplesTable flightData;

    @Before
    public void setUp() {
        flightData = mock(ExamplesTable.class);
        integration = new DynaStubsIntegration();
    }

    @Test
    public void shouldHandleSpringDST() {
        LocalDate date = DAY_BEFORE_SPRING_DST;
        int days = integration.prepareFlightSchedulesForWeekStartingMondayOnOrBeforeDay(date, flightData, false,
                ORIGIN, DESTINATION, DAYS_TO_ADD_STRING);
        assertExpectedDays(date, days);
    }

    @Test
    public void shouldHandleFallDST() {
        LocalDate date = new LocalDate(2013, OCTOBER, 31);
        int days = integration.prepareFlightSchedulesForWeekStartingMondayOnOrBeforeDay(date, flightData, false,
                ORIGIN, DESTINATION, DAYS_TO_ADD_STRING);
        assertExpectedDays(date, days);
    }

    @Test
    public void shouldHandleEndOfMonth() {
        LocalDate date = new LocalDate(2013, MARCH, 31);
        int days = integration.prepareFlightSchedulesForWeekStartingMondayOnOrBeforeDay(date, flightData, false,
                ORIGIN, DESTINATION, DAYS_TO_ADD_STRING);
        assertExpectedDays(date, days);
    }

    @Test
    public void shouldHandleEndOfYear() {
        LocalDate date = new LocalDate(2013, DECEMBER, 31);
        int days = integration.prepareFlightSchedulesForWeekStartingMondayOnOrBeforeDay(date, flightData, false,
                ORIGIN, DESTINATION, DAYS_TO_ADD_STRING);
        assertExpectedDays(date, days);
    }

    @Test
    public void shouldHandleMondayAfterDST() {
        LocalDate date = new LocalDate(2013, MARCH, 11);
        int days = integration.prepareFlightSchedulesForWeekStartingMondayOnOrBeforeDay(date, flightData, false,
                ORIGIN, DESTINATION, DAYS_TO_ADD_STRING);
        assertExpectedDays(date, days);
    }

    @Test
    public void shouldHandleMonday() {
        LocalDate date = NOW.withDayOfWeek(MONDAY);
        int days = integration.prepareFlightSchedulesForWeekStartingMondayOnOrBeforeDay(date, flightData, false,
                ORIGIN, DESTINATION, DAYS_TO_ADD_STRING);
        assertExpectedDays(date, days);
    }

    @Test
    public void shouldHandleTuesday() {
        LocalDate date = NOW.withDayOfWeek(TUESDAY);
        int days = integration.prepareFlightSchedulesForWeekStartingMondayOnOrBeforeDay(date, flightData, false,
                ORIGIN, DESTINATION, DAYS_TO_ADD_STRING);
        assertExpectedDays(date, days);
    }

    @Test
    public void shouldHandleWednesday() {
        LocalDate date = NOW.withDayOfWeek(WEDNESDAY);
        int days = integration.prepareFlightSchedulesForWeekStartingMondayOnOrBeforeDay(date, flightData, false,
                ORIGIN, DESTINATION, DAYS_TO_ADD_STRING);
        assertExpectedDays(date, days);
    }

    @Test
    public void shouldHandleThursday() {
        LocalDate date = NOW.withDayOfWeek(THURSDAY);
        int days = integration.prepareFlightSchedulesForWeekStartingMondayOnOrBeforeDay(date, flightData, false,
                ORIGIN, DESTINATION, DAYS_TO_ADD_STRING);
        assertExpectedDays(date, days);
    }

    @Test
    public void shouldHandleFriday() {
        LocalDate date = NOW.withDayOfWeek(FRIDAY);
        int days = integration.prepareFlightSchedulesForWeekStartingMondayOnOrBeforeDay(date, flightData, false,
                ORIGIN, DESTINATION, DAYS_TO_ADD_STRING);
        assertExpectedDays(date, days);
    }

    @Test
    public void shouldHandleSaturday() {
        LocalDate date = NOW.withDayOfWeek(SATURDAY);
        int days = integration.prepareFlightSchedulesForWeekStartingMondayOnOrBeforeDay(date, flightData, false,
                ORIGIN, DESTINATION, DAYS_TO_ADD_STRING);
        assertExpectedDays(date, days);
    }

    @Test
    public void shouldHandleSunday() {
        LocalDate date = NOW.withDayOfWeek(SUNDAY);
        int days = integration.prepareFlightSchedulesForWeekStartingMondayOnOrBeforeDay(date, flightData, false,
                ORIGIN, DESTINATION, DAYS_TO_ADD_STRING);
        assertExpectedDays(date, days);
    }

    @Test
    public void shouldHandleAddingDaysAndHours() {
        LocalDate date = DAY_BEFORE_SPRING_DST;
        int days = integration.prepareFlightSchedulesForWeekStartingMondayOnOrBeforeDay(date, flightData, false,
                ORIGIN, DESTINATION, "+10d24h");
        assertEquals(9, days);
    }

    @Test
    public void shouldHandleAddingWeeks() {
        LocalDate date = DAY_BEFORE_SPRING_DST;
        int days = integration.prepareFlightSchedulesForWeekStartingMondayOnOrBeforeDay(date, flightData, false,
                ORIGIN, DESTINATION, "+2w");
        assertEquals(9, days);
    }

    @Test
    public void shouldHandleAddingMonths() {
        LocalDate date = DAY_BEFORE_SPRING_DST;
        int days = integration.prepareFlightSchedulesForWeekStartingMondayOnOrBeforeDay(date, flightData, false,
                ORIGIN, DESTINATION, "+3MO");
        assertEquals(86, days);
    }

    @Test
    public void shouldHandleAddingYears() {
        LocalDate date = DAY_BEFORE_SPRING_DST;
        int days = integration.prepareFlightSchedulesForWeekStartingMondayOnOrBeforeDay(date, flightData, false,
                ORIGIN, DESTINATION, "+1y");
        assertEquals(359, days);
    }

    private void assertExpectedDays(LocalDate startDate, int days) {
        LocalDate futureDate = startDate.plusDays(DAYS_TO_ADD).withDayOfWeek(MONDAY);
        int expected = new Period(startDate, futureDate).toStandardDays().getDays();
        assertEquals(expected, days);
    }
}