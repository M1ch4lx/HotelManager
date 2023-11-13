package pl.edu.agh.hotelmanagerfrontend.model;

public class Room {

    private Integer number;

    private String description;

    private Double price;

    private Guest guest;

    public Room() {

    }

    public Room(Integer number, String description, Double price) {
        this.number = number;
        this.description = description;
        this.price = price;
    }

    public boolean isAvailable() {
        return guest == null;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    @Override
    public String toString() {
        return number + ": " + description + " | " + price
                + " | " + (isAvailable() ? "wolny" : "zajęty");
    }
}
