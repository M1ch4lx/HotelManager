package pl.edu.agh.hotelmanagerfrontend.model;

import pl.edu.agh.utils.MyMap;

import java.security.InvalidParameterException;
import java.util.Collection;

public class Hotel {

    private MyMap<Integer, Room> rooms = new MyMap<>();

    public Hotel() {
        //rooms.put(101, new Room(101, "Nice room", 300.0));
        //rooms.put(102, new Room(102, "Very nice room", 250.0));
        //rooms.put(201, new Room(201, "Mediocre room", 150.0));
        //rooms.put(202, new Room(202, "Terrible room", 100.0));
    }

    public void addRoom(Room room) {
        rooms.put(room.getNumber(), room);
    }

    public Room getRoom(Integer number) {
        var room = rooms.get(number);

        if(room == null) {
            throw new InvalidParameterException("Pokój o podanym numerze nie istnieje");
        }

        return room;
    }

    public Collection<Room> getRooms() {
        return rooms.values();
    }

    public boolean isRoomAvailable(Integer number) {
        return getRoom(number).isAvailable();
    }

    public void throwIfRoomNotAvailable(Integer number) {
        if(!getRoom(number).isAvailable()) {
            throw new InvalidParameterException("Pokój o podanym numerze jest zajęty");
        }
    }

    public void checkInGuest(Integer roomNumber, Guest guest) {
        var room = getRoom(roomNumber);

        if(!room.isAvailable()) {
            throw new InvalidParameterException("Pokój o podanym numerze jest zajęty");
        }

        room.setGuest(guest);
    }

    public void checkOutGuest(Integer roomNumber) {
        var room = getRoom(roomNumber);

        if(room.isAvailable()) {
            throw new InvalidParameterException("Pokój o podanym numerze jest już wolny, nie ma kogo wymeldować");
        }

        room.setGuest(null);
    }

    public void saveToFile() {

    }
}
