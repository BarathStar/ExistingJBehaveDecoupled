package util

class ResetPasswordData {

    public String password_1
    public String password_2
    public String token
    public String tokenStatus

    public ResetPasswordData(String password_1, String password_2,
            String token, String tokenStatus) {
        super();
        this.password_1 = password_1;
        this.password_2 = password_2;
        this.token = token;
        this.tokenStatus = tokenStatus;
    }

    public void setValues(ResetPasswordData resetPasswordData) {
        this.password_1 = resetPasswordData.password_1
        this.password_2 = resetPasswordData.password_2
        this.token = resetPasswordData.token
        this.tokenStatus = resetPasswordData.tokenStatus
    }

}
