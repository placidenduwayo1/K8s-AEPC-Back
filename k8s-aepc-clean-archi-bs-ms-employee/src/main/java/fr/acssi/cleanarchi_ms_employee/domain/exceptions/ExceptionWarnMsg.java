package fr.acssi.cleanarchi_ms_employee.domain.exceptions;

public enum ExceptionWarnMsg {
    EMPLOYEE_ALREADY_EXISTS_EXCEPTION ("Employee already exists exception"),
    EMPLOYEE_NOT_FOUND_EXCEPTION ("Employee not found exception"),
    EMPLOYEE_FIELDS_EMPTY_EXCEPTION ("Employee one ore more fields invalid exception"),
    EMPLOYEE_ASSOCIATED_PROJECTS_EXCEPTION("Employee project associated exception"),
    REMOTE_ADDRESS_API_UNAVAILABLE_EXCEPTION("Remote address api unavailable exception"),
    EMPLOYEE_UNRECOGNIZED_STATE_EXCEPTION("Employee state unrecognized exception"),
    EMPLOYEE_UNRECOGNIZED_TYPE_EXCEPTION("Employee type unrecognized exception");

    private final String exception;

    ExceptionWarnMsg(String exception) {
        this.exception = exception;
    }
    public String getException() {
        return exception;
    }
}
