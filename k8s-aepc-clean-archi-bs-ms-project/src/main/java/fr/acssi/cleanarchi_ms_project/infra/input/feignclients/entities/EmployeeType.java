package fr.acssi.cleanarchi_ms_project.infra.input.feignclients.entities;

public enum EmployeeType {
    CTO("cto"),
    CEO("ceo"),
    HR("hr"),
    TECH_M("tech-manager"),
    COM_M("com-manager"),
    EMPL("employee"),
    TAM("talent-acquis-manager"),
    SE("software-engineer");
    private final String employeeType;

    EmployeeType(String employeeType) {
        this.employeeType = employeeType;
    }
    public String getEmployeeType() {
        return employeeType;
    }
}
