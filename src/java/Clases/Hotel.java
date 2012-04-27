package Clases;

import dbcp.ConnectionManager;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class Hotel {

    private String _hotelId;
    private String _hotelName;
    private City _location;
    private int _noOfDeluxRooms;
    private int _noOfEXERooms;
    private double _deluxRoomFare_PerDay;
    private double _eXERoomFarePerDay;
    private double _hotelTax;

    public Hotel(String _hotelId, String _hotelName, City _location, int _noOfDeluxRooms, int _noOfEXERooms, double _deluxRoomFare_PerDay, double _eXERoomFarePerDay, double _hotelTax) {
        this._hotelId = _hotelId;
        this._hotelName = _hotelName;
        this._location = _location;
        this._noOfDeluxRooms = _noOfDeluxRooms;
        this._noOfEXERooms = _noOfEXERooms;
        this._deluxRoomFare_PerDay = _deluxRoomFare_PerDay;
        this._eXERoomFarePerDay = _eXERoomFarePerDay;
        this._hotelTax = _hotelTax;
    }

    public String getHotelId() {
        return this._hotelId;
    }

    public void setHotelId(String aHotelId) {
        this._hotelId = aHotelId;
    }

    public String getHotelName() {
        return this._hotelName;
    }

    public void setHotelName(String aHotelName) {
        this._hotelName = aHotelName;
    }

    public City getLocation() {
        return this._location;
    }

    public void setLocation(City aLocation) {
        this._location = aLocation;
    }

    public int getNoOfDeluxRooms() {
        return this._noOfDeluxRooms;
    }

    public void setNoOfDeluxRooms(int aNoOfDeluxRooms) {
        this._noOfDeluxRooms = aNoOfDeluxRooms;
    }

    public int getNoOfEXERooms() {
        return this._noOfEXERooms;
    }

    public void setNoOfEXERooms(int aNoOfEXERooms) {
        this._noOfEXERooms = aNoOfEXERooms;
    }

    public double getDeluxRoomFare_PerDay() {
        return this._deluxRoomFare_PerDay;
    }

    public void setDeluxRoomFare_PerDay(double aDeluxRoomFare_PerDay) {
        this._deluxRoomFare_PerDay = aDeluxRoomFare_PerDay;
    }

    public double getEXERoomFarePerDay() {
        return this._eXERoomFarePerDay;
    }

    public void setEXERoomFarePerDay(double aEXERoomFarePerDay) {
        this._eXERoomFarePerDay = aEXERoomFarePerDay;
    }

    public double getHotelTax() {
        return this._hotelTax;
    }

    public void setHotelTax(double aHotelTax) {
        this._hotelTax = aHotelTax;
    }
    
    /**
     * Method that checks if a specific room type is available in the Hotel
     * for the given dates.
     * @param fromDate
     * @param toDate
     * @param roomType 0 - Any room. 1 - Deluxe. 2 - Executive.
     * @return 
     */
    public boolean isRoomAvailable(Date fromDate, Date toDate, int roomType) throws SQLException {
        String fromDate_s = new java.text.SimpleDateFormat("yyyy-MM-dd").format(fromDate.getTime()*1000);
        String toDate_s = new java.text.SimpleDateFormat("yyyy-MM-dd").format(toDate.getTime()*1000);
        String roomName;
        String hotelRooms;
        String whereClause = "HC.HotelId='"+this._hotelId+"' AND HD.HotelId='"+this._hotelId+"' AND (HC.Dia >='"+fromDate_s+"' AND HC.Dia <='"+toDate_s+"')";
        String tablesNames = "Tbl_Hotel_Details_GroupNo HD, Tbl_Hotel_Calendar HC";
        String fields[];
        ArrayList fieldsArrayList = new ArrayList();
        boolean isAvailable = true;
        switch(roomType) {
            case 1:
                roomName = "HC.CuartosExec as Total";
                hotelRooms = "HD.NoOfEXERooms as Htotal";
                break;
            case 2:
                roomName = "HC.CuartosDelux as Total";
                hotelRooms = "HD.NoOfDeluxRooms as Htotal";
                break;
            case 3:
            default:
                roomName = "(HC.CuartosDelux + HC.CuartosDelux) as Total"; 
                hotelRooms = "(HD.NoOfDeluxRooms + HD.NoOfEXERooms) as Htotal";
        }
        fieldsArrayList.add(roomName);
        fieldsArrayList.add(hotelRooms);
        fields = new String[fieldsArrayList.size()];
        fieldsArrayList.toArray(fields);
        ResultSet rs = ConnectionManager.select(fields, tablesNames, whereClause);
        if(rs.next()){
            do {
                isAvailable = isAvailable && (rs.getInt("Total") < rs.getInt("Htotal"));
            } while(rs.next());
        }
        return isAvailable;
    }
    /**
     * Method that creates an object according to its defined WHERE clause and
     * received variables
     *
     * @param _hotelId The hotel's ID
     * @throws SQLException
     * @return h An object created of a the variables that correspond to each
     * new Hotel
     */
    public static Hotel getHotel(String _hotelId) throws SQLException {
        ResultSet rs = ConnectionManager.selectAllColumns("Tbl_Hotel_Details_GroupNo", "HotelId='" + _hotelId + "'");
        if (rs.next()) {
            Hotel h = new Hotel(rs.getString("HotelId"), 
                                            rs.getString("HotelName"), 
                                            City.getCity(rs.getString("Location")), 
                                            rs.getInt("NoOfDeluxRooms"),
                                            rs.getInt("NoOfEXERooms"), 
                                            rs.getDouble("deluxRoomFare_PerDay"), 
                                            rs.getDouble("EXERoomFare_PerDay"), 
                                            rs.getDouble("HotelTax"));
            return h;
        } else {
            return null;
        }
    }

    /**
     * Method that creates an object according to its defined WHERE clause and
     * received variables
     *
     * @param _cityCode The City where the hotel(s) should be looked
     * @throws SQLException
     * @return h An array of Hotel objects that contain the info of each created
     * Hotel
     */
    public static Hotel[] getHotels(City _location) throws SQLException {
        ResultSet rs = ConnectionManager.selectAllColumns("Tbl_Hotel_Details_GroupNo", "location='" + _location.getCityCode() +"'");
        if (rs.next()) {
            ArrayList hotelArrayList = new ArrayList();
            Hotel h[];
            do {
                hotelArrayList.add(new Hotel(rs.getString("HotelId"), 
                                            rs.getString("HotelName"), 
                                            City.getCity(rs.getString("Location")), 
                                            rs.getInt("NoOfDeluxRooms"),
                                            rs.getInt("NoOfEXERooms"), 
                                            rs.getDouble("deluxRoomFare_PerDay"), 
                                            rs.getDouble("EXERoomFare_PerDay"), 
                                            rs.getDouble("HotelTax")));
            } while (rs.next());
            if(hotelArrayList.size()>0){
                h = new Hotel[hotelArrayList.size()];
                hotelArrayList.toArray(h);
                return h;
            }            
        }
        return null;
    }
    
    public static double getDiscount(String hId, Date DoB, Date DoC, double pd, int tipo)throws SQLException{
        Hotel h = Hotel.getHotel(hId);
        double discount = 1;
        double totalCuartos=0;
        double cuartosActual=0;
        double capacidad;
        double fare =0;
        
        /*Se declaran variables relacionadas con las fechas.
         *Las que empiezan con "db" se relacionan con DateOfBooking.
         *Las que empiezan con "dc" se relacionan con DateOfCheckIn.  
         */
        int dby = DoB.getYear();
        int dcy = DoC.getYear();
        int dbm = DoB.getMonth();
        int dcm = DoC.getMonth();
        int dbd = DoB.getDay();
        int dcd = DoC.getDay();
        //Se suma descuento por antelación.
        if(dby == dcy+1){
            dcm+=12;
            if(dbm>dcm+2)
                discount-=.2;
            
        }else if(dby == dcy){
            if(dbm>dcm+2)
                discount-=2;
        }else{
            discount-=2;
        }
        
        //Se suma descuento por tipo de reservación.
        discount-= 0.1*tipo;
        
        //Se suma descuento por paquete.
        discount-=pd;
        
        //Se busca el Tbl_Hotel_Calendar para conseguir cuartos reservados actualmente.
        ResultSet rs = ConnectionManager.selectAllColumns("Tbl_Hotel_Calendar", "HotelId= " + hId);
        if (rs.next()) {
            if(tipo==0)
                cuartosActual = (double)rs.getInt("CuartosDelux");
            else
                cuartosActual = (double)rs.getInt("CuartosExec");
        }
        //Se consiguen datos de Tbl_Hotel_Details_GroupNo para capacidad total.
        rs = ConnectionManager.selectAllColumns("Tbl_Hotel_Details_GroupNo","HotelId"+hId);
        if (rs.next()) {
            if(tipo==0)
                totalCuartos = (double)rs.getInt("NoOfDeluxRooms");
            else
                totalCuartos = (double)rs.getInt("NoOfEXERooms");
            
        }
        //80% = 20% mas
        //-10% = 20% menos
        //Se agrega descuento/aumento por capacidad.
        capacidad = cuartosActual/totalCuartos;
        if(capacidad>0.8)
            discount += 0.2;
        else if(capacidad<0.1)
            discount -= 0.2;
        
        return discount;
    }
}
