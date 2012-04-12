package Clases;

import dbcp.ConnectionManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Hotel_Discounts {

    private Hotel _hotelID;
    private int _roomsBooked;
    private double _discount;

    public Hotel_Discounts(Hotel _hotelID, int _roomsBooked, double _discount) {
        this._hotelID = _hotelID;
        this._roomsBooked = _roomsBooked;
        this._discount = _discount;
    }

    
    public Hotel getHotelID() {
        return this._hotelID;
    }

    public void setHotelID(Hotel aHotelID) {
        this._hotelID = aHotelID;
    }

    public int getRoomsBooked() {
        return this._roomsBooked;
    }

    public void setRoomsBooked(int aRoomsBooked) {
        this._roomsBooked = aRoomsBooked;
    }

    public double getDiscount() {
        return this._discount;
    }

    public void setDiscount(double aDiscount) {
        this._discount = aDiscount;
    }

    /**
     * Method that receives _hotelId and returns an array of Hotel_Discounts
     *
     * @param _hotelId to receive _hotelId from Hotel
     * @return hd array that contains Hotel_Discounts
     * @throws SQLException
     */
    public static Hotel_Discounts[] getHotel_Discounts(int _hotelId) throws SQLException {
        ResultSet rs = ConnectionManager.selectAllColumns("Hotel_Discounts", "HotelID= " + _hotelId);
        if (rs.next()) {
            ArrayList Hotel_DiscountsArrayList = new ArrayList();
            Hotel_Discounts hd[];
            do {
                Hotel_DiscountsArrayList.add(new Hotel_Discounts(Hotel.getHotel(rs.getString("Hotel_id")), rs.getInt("RoomsBooked"), rs.getDouble("Discount")));
            } while (rs.next());
            hd = (Hotel_Discounts[]) Hotel_DiscountsArrayList.toArray();
            return hd;
        } else {
            return null;
        }
    }
}