package pl.edu.agh.hotelmanagerfrontend.model;

public class Guest {

    private String firstName;

    private String lastName;

    private int phoneNumber;

    public Guest() {

    }

    public Guest(String data) {
        var fields = data.split(" ");
        firstName = fields[0];
        lastName = fields[1];
        phoneNumber = Integer.parseInt(fields[2]);
    }

    public Guest(String firstName, String lastName, int phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " " + phoneNumber;
    }
}
