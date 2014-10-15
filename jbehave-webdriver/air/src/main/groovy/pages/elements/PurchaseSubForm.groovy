package pages.elements

import fixtures.air.ReservationSpecification

public interface PurchaseSubForm {
    public void fillForm()
    public void fillFormBasedOn(ReservationSpecification specification)
}