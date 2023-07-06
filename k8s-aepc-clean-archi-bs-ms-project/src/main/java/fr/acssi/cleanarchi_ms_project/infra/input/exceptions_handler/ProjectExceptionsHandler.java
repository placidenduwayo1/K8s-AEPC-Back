package fr.acssi.cleanarchi_ms_project.infra.input.exceptions_handler;

import fr.acssi.cleanarchi_ms_project.domain.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProjectExceptionsHandler {
    @ExceptionHandler(value = ProjectAlreadyExistsException.class)
    private ResponseEntity<String> handleProjectAlreadyExistsException() {
        return new ResponseEntity<>(ExceptionWarnMsg
                .PROJECT_ALREADY_EXISTS_EXCEPTION
                .getException(),
                HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(value = ProjectNotFoundException.class)
    private ResponseEntity<Object> handleProjectNotFoundException() {
        return new ResponseEntity<>(ExceptionWarnMsg
                .PROJECT_NOT_FOUND_EXCEPTION
                .getException(),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = ProjectFieldsEmptyException.class)
    private ResponseEntity<Object> handleProjectFieldsEmptyException() {
        return new ResponseEntity<>(ExceptionWarnMsg
                .PROJECT_FIELDS_EMPTY_EXCEPTION
                .getException(),
                HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(value = ProjectPriorityUnrecognisedException.class)
    private ResponseEntity<String> handleProjectPriorityUnrecognisedException() {
        return new ResponseEntity<>(
                ExceptionWarnMsg.
                        PROJECT_PRIORITY_UNRECOGNIZED_EXCEPTION
                        .getException(),
                HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(value = ProjectStateUnrecognisedException.class)
    private ResponseEntity<String> handleProjectStateUnrecognisedException() {
        return new ResponseEntity<>(
                ExceptionWarnMsg.
                        PROJECT_STATE_UNRECOGNIZED_EXCEPTION
                        .getException(),
                HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(value = RemoteEmployeeApiUnavailableException.class)
    private ResponseEntity<Object> handleProjectCreationErrorDueToEmployeeAPI(
            RemoteEmployeeApiUnavailableException e) {
        return new ResponseEntity<>(
                e.getMessage(),
                HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(value = RemoteCompanyApiUnavailableException.class)
    private ResponseEntity<Object> handleProjectCreationErrorDueToCompanyAPI(
            RemoteCompanyApiUnavailableException e) {
        return new ResponseEntity<>(
                e.getMessage(),
                HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(value = RemoteEmployeeStateNotAcceptableException.class)
    private ResponseEntity<String> handleProjectCreationErrorDueToStateOfEmployeeException() {
        return new ResponseEntity<>(ExceptionWarnMsg
                .REMOTE_EMPLOYEE_STATE_NOT_ACCEPTABLE_EXCEPTION
                .getException(),
                HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(value = ProjectIsAssignedToCompanyException.class)
    private ResponseEntity<Object> handleCompanyIsAssociatedToProjectException() {
        return new ResponseEntity<>(ExceptionWarnMsg
                .PROJECT_ALREADY_ASSIGNED_COMPANY_EXCEPTION
                .getException(),
                HttpStatus.NOT_ACCEPTABLE);
    }
@ExceptionHandler(value = RemoteCompanyConnectionStateUnauthorized.class)
    private ResponseEntity<String> handleRemoteCompanyConnectStateUnauthorized() {
        return new ResponseEntity<>(ExceptionWarnMsg
                .REMOTE_COMPANY_CONNECT_STATE_UNAUTHORIZED_EXCEPTION
                .getException(),
                HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(value = ProjectIsAssignedToEmployeeException.class)
    private ResponseEntity<String> handleEmployeeIsAssociatedToProjectException() {
        return new ResponseEntity<>(ExceptionWarnMsg
                .PROJECT_ALREADY_ASSIGNED_EMPLOYEE_EXCEPTION
                .getException(),
                HttpStatus.NOT_ACCEPTABLE);
    }
}
