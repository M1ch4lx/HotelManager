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
        final String outputFilePath = "hotel.xlsx";
        try(FileOutputStream fileOutputStream = new FileOutputStream(outputFilePath)) {

            String[] header = { "Number", "Description", "Price", "Available", "Guest" };

            try(Workbook workbook = new XSSFWorkbook())
            {
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
            }
        }
        catch(IOException exception) {
            throw new InvalidParameterException(exception.getMessage());
        }
    }

    public void loadFromFile() {
        final String inputFilePath = "hotel.xlsx";
        try (var fileInputStream = new FileInputStream(inputFilePath)) {

            try(var workbook = WorkbookFactory.create(fileInputStream)) {
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
        }
        catch(IOException exception) {
            throw new InvalidParameterException(exception.getMessage());
        }
    }
}
