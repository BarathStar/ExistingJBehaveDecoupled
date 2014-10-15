package util

public enum CarVendor {
    // These names match the values returned on the car search page for elements with class "car_vendor_sprite"
    Alamo("ALAMO"),
    Avis("AVIS"),
    Budget("BUDGET"),
    Dollar("DOLLAR"),
    Enterprise("ET"),
    Fox("FX"),
    Hertz("HERTZ"),
    National("ZL"),
    Thrifty("THRIFTY")

    private String vendorCode

    private CarVendor(String vendorCode) {
        this.vendorCode = vendorCode
    }

    public String vendorCode() {
        return vendorCode
    }

    static public CarVendor retrieveVendorForCode(String vendorCode) {
        return CarVendor.Avis.vendorCode().equals(vendorCode) ? CarVendor.Avis :
               CarVendor.Alamo.vendorCode().equals(vendorCode) ? CarVendor.Alamo :
               CarVendor.Budget.vendorCode().equals(vendorCode) ? CarVendor.Budget :
               CarVendor.Dollar.vendorCode().equals(vendorCode) ? CarVendor.Dollar :
               CarVendor.Enterprise.vendorCode().equals(vendorCode) ? CarVendor.Enterprise :
               CarVendor.Fox.vendorCode().equals(vendorCode) ? CarVendor.Fox :
               CarVendor.Hertz.vendorCode().equals(vendorCode) ? CarVendor.Hertz :
               CarVendor.National.vendorCode().equals(vendorCode) ? CarVendor.National :
               CarVendor.Thrifty.vendorCode().equals(vendorCode) ? CarVendor.Thrifty : null;
    }

    static public CarVendor retrieveVendorForName(String vendorName) {
        return CarVendor.Avis.toString().equalsIgnoreCase(vendorName) ? CarVendor.Avis :
            CarVendor.Alamo.toString().equalsIgnoreCase(vendorName) ? CarVendor.Alamo :
            CarVendor.Budget.toString().equalsIgnoreCase(vendorName) ? CarVendor.Budget :
            CarVendor.Dollar.toString().equalsIgnoreCase(vendorName) ? CarVendor.Dollar :
            CarVendor.Enterprise.toString().equalsIgnoreCase(vendorName) ? CarVendor.Enterprise :
            CarVendor.Fox.toString().equalsIgnoreCase(vendorName) ? CarVendor.Fox :
            CarVendor.Hertz.toString().equalsIgnoreCase(vendorName) ? CarVendor.Hertz :
            CarVendor.National.toString().equalsIgnoreCase(vendorName) ? CarVendor.National :
            CarVendor.Thrifty.toString().equalsIgnoreCase(vendorName) ? CarVendor.Thrifty : null;
    }

}