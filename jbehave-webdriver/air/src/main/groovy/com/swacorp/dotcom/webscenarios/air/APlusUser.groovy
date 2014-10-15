package com.swacorp.dotcom.webscenarios.air

class APlusUser {

    private final String memberId;
    private final String password;
    private String email;

    public APlusUser(String memberId, String email, String password) {
        this.memberId = memberId;
        this.email = email;
        this.password = password;
    }

    public String getMemberId() {
        return memberId;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}
