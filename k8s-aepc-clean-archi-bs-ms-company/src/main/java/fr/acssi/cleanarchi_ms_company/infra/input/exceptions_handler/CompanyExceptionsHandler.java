package fr.acssi.cleanarchi_ms_company.infra.input.exceptions_handler;

import fr.acssi.cleanarchi_ms_company.domain.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CompanyExceptionsHandler {

    @ExceptionHandler(value = CompanyAlreadyExistsException.class)
    private ResponseEntity<String> handleCompanyAlreadyExistsException(){
        return new ResponseEntity<>(ExceptionsWarnMsg
                .COMPANY_ALREADY_EXISTS_EXCEPTION
                .getException(),
                HttpStatus.NOT_ACCEPTABLE);
    }
    @ExceptionHandler(value = CompanyNotFoundException.class)
    private ResponseEntity<String> handleCompanyNotFoundException(){
        return new ResponseEntity<>(ExceptionsWarnMsg
                .COMPANY_NOT_FOUND_EXCEPTION
                .getException(),
                HttpStatus.NOT_ACCEPTABLE);
    }
    @ExceptionHandler(value = CompanyFieldsEmptyException.class)
    private ResponseEntity<String> handleCompanyFieldsEmptyException(){
        return new ResponseEntity<>(ExceptionsWarnMsg
                .COMPANY_FIELDS_EMPTY_EXCEPTION
                .getException(),
                HttpStatus.NOT_ACCEPTABLE);
    }
    @ExceptionHandler(value = CompanyAssociedProjectsException.class)
    private ResponseEntity<String> handleCompanyAssociatedProjectsException(){
        return new ResponseEntity<>(ExceptionsWarnMsg
                .COMPANY_ASSOCIATED_PROJECT_EXCEPTION
                .getException(),
                HttpStatus.NOT_ACCEPTABLE);
    }
    @ExceptionHandler(value = CompanyTypeUnrecognizedException.class)
    private ResponseEntity<String> handleCompanyTypeUnrecognizedException(){
        return new ResponseEntity<>(ExceptionsWarnMsg
                .COMPANY_TYPE_UNRECOGNIZED_EXCEPTION
                .getException(),
                HttpStatus.NOT_ACCEPTABLE );
    }
    @ExceptionHandler(value = CompanyConnectStateUnrecognizedException.class)
    private ResponseEntity<String> handleCompanyConnectionStateUnrecognizedException(){
        return new ResponseEntity<>(ExceptionsWarnMsg
                .COMPANY_CONNECT_STATE_UNRECOGNIZED_EXCEPTION
                        .getException(),
                HttpStatus.NOT_ACCEPTABLE);
    }
}
