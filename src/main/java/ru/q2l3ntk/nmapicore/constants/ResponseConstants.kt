package ru.q2l3ntk.nmapicore.constants

enum class ResponseConstants(val value: String) {
    SUCCESS("success"),
    ERROR("error"),
    USERNAME_UNAVAILABLE("USR_001"),
    INVALID_USER_ID("USR_002"),
    EMPTY_STATUS("USR_003"),
    MESSAGE_EMPTY("MES_001"),
    MESSAGE_RECIPIENT_INVALID("MES_002"),
    ACCOUNT_DEACTIVATED("GLO_001")
}