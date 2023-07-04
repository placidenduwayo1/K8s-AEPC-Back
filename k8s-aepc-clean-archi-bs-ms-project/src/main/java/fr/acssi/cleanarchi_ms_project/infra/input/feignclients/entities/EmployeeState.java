package fr.acssi.cleanarchi_ms_project.infra.input.feignclients.entities;

public enum EmployeeState {
    ACTIVE("active"),
    HISTORIZED("historized");
    private String employeeState;
    EmployeeState(String employeeState) {
        this.employeeState = employeeState;
    }
    public String getEmployeeState() {
        return employeeState;
    }
}
