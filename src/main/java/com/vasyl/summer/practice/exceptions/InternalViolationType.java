package com.vasyl.summer.practice.exceptions;

public enum InternalViolationType {

    //Auth
    INVALID_PASSWORD_EXCEPTION(1001, "Invalid password"),
    RESTORE_PASSWORD_TOKEN_NOT_FOUND(1002, "Invalid restore password code"),
    INVALID_2FA_CODE_EXCEPTION(1007, "Invalid 2fa code"),
    INVALID_MAIL_CODE_EXCEPTION(1008, "Invalid mail code"),
    MAIL_CODE_NOT_FOUND_EXCEPTION(1009, "Mail code not found"),
    INVALID_REFRESH_TOKEN(1010, "Invalid refresh token"),
    EXPIRED_REFRESH_TOKEN(1011, "Expired refresh token"),
    INVALID_DEVICE_REFRESH_TOKEN(1012, "Invalid device of refresh token"),

    //Registration
    EMAIL_IN_USE_EXCEPTION(2001, "Email address already in use"),
    EMAIL_NOT_CONFIRM_EXCEPTION(2002, "Please verify your email"),
    NICKNAME_IN_USE_EXCEPTION(2003, "Nickname already in use"),
    CONFIRM_EMAIL_TOKEN_NOT_FOUND(2004, "Confirm email token not found"),
    EXPIRED_OTP_CODE(2005, "Expired otp code"),
    SECURITY_TOKEN_NOT_FOUND(2006, "Security token not found"),
    INVALID_SECURITY_TOKEN(2007, "Invalid security token"),
    INVALID_ATTEMPTS_SECURITY_TOKEN_NUMBER(2008, "Invalid attempts security token number"),
    USER_BANNED(2009, "User is banned"),
    USER_DELETED(2010, "User is deleted"),

    //User
    USER_NOT_FOUND_EXCEPTION(3001, "User not found"),
    NOT_ENOUGH_RIGHTS(3002, "Not enough rights"),
    KYC_USER_ALREADY_EXISTS(3003, "Kyc user already exists"),
    FIREBASE_TOKEN_NOT_FOUND(3004, "Firebase token not found"),
    INVALID_ACCOUNT_ID(3005, "User not found with this account id"),
    SECTION_NOT_FOUND(4001, "Section not found"),
    ACTIVITY_NOT_FOUND(5001, "Activity not found"),
    SPORTS_COMPLEX_NOT_FOUND(6001, "Sports complex not found"),
    USER_QUESTION_NOT_FOUND(7001, "User question not found"),
    USER_FEEDBACK_NOT_FOUND(8001, "User feedback not found"),
    NOT_ENOUGH_RIGHTS_NEED_SUPER_ADMIN(9001, "Not enough rights, need super admin");

    private final int code;
    private final String message;

    InternalViolationType(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "ViolationType{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
