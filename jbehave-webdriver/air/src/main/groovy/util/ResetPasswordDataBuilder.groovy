package util

class ResetPasswordDataBuilder {

    public ResetPasswordData resetPasswordData

    public ResetPasswordDataBuilder(ResetPasswordData resetPasswordData){
        this.resetPasswordData = resetPasswordData
    }

    ResetPasswordDataBuilder withPasswordWithValidCharacters() {
        this.resetPasswordData.password_1 = this.resetPasswordData.password_2 = "Test123"
        return this
    }

    ResetPasswordDataBuilder withPasswordWithInvalidCharacters() {
        this.resetPasswordData.password_1 = this.resetPasswordData.password_2 = "blah&blah@"
        return this
    }

    ResetPasswordDataBuilder withPasswordBlackListed(String blacklistPassword) {
        this.resetPasswordData.password_1 = this.resetPasswordData.password_2 = blacklistPassword
        return this
    }

    ResetPasswordDataBuilder withNotMatchingPasswords() {
        this.resetPasswordData.password_1 = "Test123"
        this.resetPasswordData.password_2 = "Test124"
        return this
    }

    ResetPasswordData build() {
        return new ResetPasswordData(
            resetPasswordData.password_1,
            resetPasswordData.password_2,
            resetPasswordData.token,
            resetPasswordData.tokenStatus
        )
    }

}
