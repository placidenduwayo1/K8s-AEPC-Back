package fr.acssi.cleanarchi_ms_project.domain.exceptions_metiers;

public enum ExceptionWarnMsg {
    PROJECT_NOT_FOUND_EXCEPTION("project not found exception"),
    PROJECT_ALREADY_EXISTS_EXCEPTION("project already exists exception"),
    PROJECT_FIELDS_EMPTY_EXCEPTION("project one or more fields invalid exception"),
    REMOTE_EMPLOYEE_API_EXCEPTION("remote employee to assign to project unavailable exception"),
    REMOTE_EMPLOYEE_STATE_NOT_ACCEPTABLE_EXCEPTION("remote employee state invalid exception "),
    PROJECT_ALREADY_ASSIGNED_EMPLOYEE_EXCEPTION("Project, employee already associated project exception"),
    PROJECT_ALREADY_ASSIGNED_COMPANY_EXCEPTION("Project, company already associated exception"),
    REMOTE_COMPANY_API_UNAVAILABLE_EXCEPTION("Remote company not available"),
    PROJECT_PRIORITY_UNRECOGNIZED_EXCEPTION("Project priority unrecognized exception"),
    PROJECT_STATE_UNRECOGNIZED_EXCEPTION("Project state unrecognized exception");

    private final String exception;

    ExceptionWarnMsg(String exception) {
        this.exception = exception;
    }

    public String getException() {
        return exception;
    }
}
