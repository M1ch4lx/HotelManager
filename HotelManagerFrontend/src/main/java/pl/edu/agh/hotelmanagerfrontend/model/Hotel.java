package pl.edu.agh.hotelmanagerfrontend.model;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import pl.edu.agh.utils.MyMap;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.Collection;

public class Hotel {

    private MyMap<Integer, Room> rooms = new MyMap<>();

    public Hotel() {
        // rooms.put(101, new Room(101, "Nice room", 300.0));
        // rooms.put(102, new Room(102, "Very nice room", 250.0));
        // rooms.put(201, new Room(201, "Mediocre room", 150.0));
        // rooms.put(202, new Room(202, "Terrible room", 100.0));

        loadFromFile();
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
        try {
            final String outputFilePath = "hotel.xlsx";

            String[] header = { "Number", "Description", "Price", "Available", "Guest" };

            FileOutputStream fileOutputStream = new FileOutputStream(outputFilePath);

            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet();

            Row headerRow = sheet.createRow(0);
            {
                int columnCount = 0;
                for(String columnName : header) {

                    Cell cell = headerRow.createCell(columnCount++);
                    cell.setCellValue(columnName);
                }
            }

            int rowCount = 1;
            for(var room : rooms.values()) {

                Row row = sheet.createRow(rowCount++);

                row.createCell(0).setCellValue(room.getNumber());
                row.createCell(1).setCellValue(room.getDescription());
                row.createCell(2).setCellValue(room.getPrice());
                row.createCell(3).setCellValue(room.isAvailable());
                row.createCell(4).setCellValue(room.getGuest() == null ? " " : room.getGuest().toString());
            }

            workbook.write(fileOutputStream);

            fileOutputStream.close();
        }
        catch(IOException exception) {
            throw new InvalidParameterException(exception.getMessage());
        }
    }

    public void loadFromFile() {
        try {
            final String inputFilePath = "hotel.xlsx";

            var fileInputStream = new FileInputStream(inputFilePath);

            var workbook = WorkbookFactory.create(fileInputStream);

            fileInputStream.close();

            var sheet = workbook.getSheetAt(0);

            for(var rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {

                var row = sheet.getRow(rowIndex);

                var room = new Room();

                room.setNumber((int)row.getCell(0).getNumericCellValue());
                room.setDescription(row.getCell(1).getStringCellValue());
                room.setPrice(row.getCell(2).getNumericCellValue());

                boolean available = row.getCell(3).getBooleanCellValue();

                if(!available) {
                    room.setGuest(
                            new Guest(row.getCell(4).getStringCellValue())
                    );
                }

                addRoom(room);
            }
        }
        catch(IOException exception) {
            throw new InvalidParameterException(exception.getMessage());
        }
    }
}
