package pages

import fixtures.air.ReservationSpecification
import org.jbehave.web.selenium.WebDriverProvider
import pages.elements.PurchaseSubForm

abstract class SortablePage extends BasePage implements PurchaseSubForm, Comparable {

    protected static final int PASSENGER_INFO_ORDER = 1
    protected static final int DRIVER_INFO_ORDER = 2
    protected static final int CONTACT_INFO_ORDER = 3
    protected static final int CREDIT_CARD_INFO_ORDER = 4
    protected static final int BILLING_INFO_ORDER = 5
    protected static final int TRIP_NAME_INFO_ORDER = 6
    protected static final int INTERNAL_REFERENCE_NUMBER_ORDER = 7
    protected static final int GIFT_CARD_CONTACT_INFO_ORDER = 8

    protected int order

    SortablePage(WebDriverProvider driverProvider, int order) {
        super(driverProvider)
        this.order = order
    }

    public int compareTo(Object t) {
        return this.order - ((SortablePage) t).order
    }

    void fillFormBasedOn(ReservationSpecification specification) {
        fillForm()
    }
}
