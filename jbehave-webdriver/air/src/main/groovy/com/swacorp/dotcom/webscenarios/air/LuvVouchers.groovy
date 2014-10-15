package com.swacorp.dotcom.webscenarios.air

class LuvVouchers {

    private final String voucherNumber;
    private final String securityCode;

    public LuvVouchers(String voucherNumber, String securityCode) {
        this.voucherNumber = voucherNumber;
        this.securityCode = securityCode;
    }

    public String getVoucherNumber() {
        return voucherNumber;
    }

    public String getSecurityCode() {
        return securityCode;
    }
}
