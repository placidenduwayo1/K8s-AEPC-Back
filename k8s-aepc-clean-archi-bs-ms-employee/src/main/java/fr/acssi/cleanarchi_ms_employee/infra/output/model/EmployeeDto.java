package fr.acssi.cleanarchi_ms_employee.infra.output.model;
public class EmployeeDto {
    private String firstname;
    private String lastname;
    private String employeeState;
    private String employeeType;
    private String addressID;
    public EmployeeDto() {
    }
    public EmployeeDto(String firstname, String lastname, String employeeState,
                       String employeeType, String addressID) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.employeeState = employeeState;
        this.employeeType = employeeType;
        this.addressID = addressID;
    }

      public String getFirstname() {
          return firstname;
      }

      public void setFirstname(String firstname) {
          this.firstname = firstname;
      }

      public String getLastname() {
          return lastname;
      }

      public void setLastname(String lastname) {
          this.lastname = lastname;
      }

      public String getEmployeeState() {
          return employeeState;
      }

      public void setEmployeeState(String employeeState) {
          this.employeeState = employeeState;
      }

      public String getEmployeeType() {
          return employeeType;
      }

      public void setEmployeeType(String employeeType) {
          this.employeeType = employeeType;
      }

      public String getAddressID() {
          return addressID;
      }

      public void setAddressID(String addressID) {
          this.addressID = addressID;
      }
  }
