package util

public enum CarRentalType {

    ECONOMY(0),
    Compact(1),
    MidSize(2),
    FullSize(3),
    Premium(4),
    Suv(5),
    Luxury(6),
    MiniVan(7),
    Convertible(8),
    FullSizeVan(9)

    private int carTypeColumnNumber

    private CarRentalType(int carTypeColumnNumber) {
      this.carTypeColumnNumber = carTypeColumnNumber
    }

    public int carTypeColumnNumber() {
      return carTypeColumnNumber
    }

    static public CarRentalType retrieveCarRentalTypeForCode(String rentalType) {
        CarRentalType.values().find{
            return it.name().equals(rentalType)
        }
    }

}