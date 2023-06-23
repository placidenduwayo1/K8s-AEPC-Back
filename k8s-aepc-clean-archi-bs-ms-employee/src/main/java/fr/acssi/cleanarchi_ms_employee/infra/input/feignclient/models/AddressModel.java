package fr.acssi.cleanarchi_ms_employee.infra.input.feignclient.models;

public class AddressModel {
    private String addressID;
    private int num;
    private String street;
    private int pb;
    private String city;
    private String country;

    public String getAddressID() {
        return addressID;
    }

    public void setAddressID(String addressID) {
        this.addressID = addressID;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getPb() {
        return pb;
    }

    public void setPb(int pb) {
        this.pb = pb;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Remote-API:[" +
                "ID='" + addressID + '\'' +
                ", num=" + num +
                ", street='" + street + '\'' +
                ", pb=" + pb +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ']';
    }
}
