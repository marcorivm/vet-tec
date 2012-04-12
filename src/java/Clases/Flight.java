package Clases;

import dbcp.ConnectionManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

public class Flight {

    private int _flight_No;
    private String _airline_Name;
    private City _source;
    private City _destination;
    private Time _departure_Time;
    private Time _arrival_Time;
    private int _total_Seats;
    private double _adult_Fare;
    private double _child_Fare;
    private double _airport_Tax;

    public Flight(int _flight_No, String _airline_Name, City _source, City _destination,
            Time _departure_Time, Time _arrival_Time, int _total_Seats,
            double _adult_Fare, double _child_Fare, double _airport_Tax) {
        this._flight_No = _flight_No;
        this._airline_Name = _airline_Name;
        this._source = _source;
        this._destination = _destination;
        this._departure_Time = _departure_Time;
        this._arrival_Time = _arrival_Time;
        this._total_Seats = _total_Seats;
        this._adult_Fare = _adult_Fare;
        this._child_Fare = _child_Fare;
        this._airport_Tax = _airport_Tax;
    }

    public int getFlight_No() {
        return this._flight_No;
    }

    public void setFlight_No(int aFlight_No) {
        this._flight_No = aFlight_No;
    }

    public String getAirline_Name() {
        return this._airline_Name;
    }

    public void setAirline_Name(String aAirline_Name) {
        this._airline_Name = aAirline_Name;
    }

    public City getSource() {
        return this._source;
    }

    public void setSource(City aSource) {
        this._source = aSource;
    }

    public City getDestination() {
        return this._destination;
    }

    public void setDestination(City aDestination) {
        this._destination = aDestination;
    }

    public Time getArrival_Time() {
        return this._arrival_Time;
    }

    public void setArrival_Time(Time aArrival_Time) {
        this._arrival_Time = aArrival_Time;
    }

    public Time getDeparture_Time() {
        return this._departure_Time;
    }

    public void setDeparture_Time(Time aDeparture_Time) {
        this._departure_Time = aDeparture_Time;
    }

    public int getTotal_Seats() {
        return this._total_Seats;
    }

    public void setTotal_Seats(int aTotal_Seats) {
        this._total_Seats = aTotal_Seats;
    }

    public double getAdult_Fare() {
        return this._adult_Fare;
    }

    public void setAdult_Fare(double aAdult_Fare) {
        this._adult_Fare = aAdult_Fare;
    }

    public double getChild_Fare() {
        return this._child_Fare;
    }

    public void setChild_Fare(double aChild_Fare) {
        this._child_Fare = aChild_Fare;
    }

    public double getAirport_Tax() {
        return this._airport_Tax;
    }

    public void setAirport_Tax(double aAirport_Tax) {
        this._airport_Tax = aAirport_Tax;
    }

    /**
     * Method that creates an object according to its defined WHERE clause and
     * received variables
     *
     * @param id The Flight's ID
     * @throws SQLException
     * @return f An object created out of the variables of the Flights class
     */
    public static Flight getFlight(int id) throws SQLException {
        ResultSet rs = ConnectionManager.selectAllColumns("Flight", "_flightNo= " + id);
        if (rs.next()) {
            Flight f = new Flight(rs.getInt("_flight_No"), rs.getString("_airline_Name"), City.getCity(rs.getString("_source")), City.getCity(rs.getString("_destination")), rs.getTime("_departure_Time"), rs.getTime("_arrival_Time"),
                    rs.getInt("_total_Seats"), rs.getDouble("_adult_Fare"), rs.getDouble("_child_Fare"), rs.getDouble("_airport_Tax"));
            return f;
        } else {
            return null;
        }
    }

    /**
     * Method that creates an object according to its defined WHERE clause and
     * received variables
     *
     * @param _source The source city of the flight
     * @param _destination The destination of the flight
     * @throws SQLException
     *
     * @return f An array of Flight objects that contains the variables in the
     * Flight class
     */
    public static Flight[] getFlights(City _source, City _destination) throws SQLException {
        ResultSet rs = ConnectionManager.selectAllColumns("Flight", "source= " + _source + " and destination= " + _destination); //falta poner que haya un mes de diferencia, no estoy seguro de como
        if (rs.next()) {
            ArrayList flightArrayList = new ArrayList();
            Flight f[];
            do {
                flightArrayList.add(new Flight(rs.getInt("_flightNo"), rs.getString("_airline_Name"), City.getCity(rs.getString("_source")), City.getCity(rs.getString("_destination")), rs.getTime("_departure_Time"), rs.getTime("_arrival_Time"),
                        rs.getInt("_total_Seats"), rs.getDouble("_adult_Fare"), rs.getDouble("_child_Fare"), rs.getDouble("_airport_Tax")));
            } while (rs.next());
            f = (Flight[]) flightArrayList.toArray();
            return f;
        } else {
            return null;
        }
    }

    /**
     * Method that creates an object according to its defined WHERE clause and
     * received variables
     *
     * @param _source The source city of the flight
     * @param _destination The destination of the flight
     * @param _From timestap value
     * @param _To timestap value
     * @throws SQLException
     * @return f An array of Flight objects that contains the variables in the
     * Flight class
     */
    public static Flight[] getFlights(City _source, City _destination, Time _From, Time _To) throws SQLException {
        ResultSet rs = ConnectionManager.selectAllColumns("Flight", "source= " + _source + "AND _destination= " + _destination + "AND from= " + _From + "AND to= " + _To);
        if (rs.next()) {
            ArrayList flightArrayList = new ArrayList();
            Flight f[];
            do {
                flightArrayList.add(new Flight(rs.getInt("_flightNo"), rs.getString("_airline_Name"), City.getCity(rs.getString("_source")), City.getCity(rs.getString("_destination")), rs.getTime("_departure_Time"), rs.getTime("_arrival_Time"),
                        rs.getInt("_total_Seats"), rs.getDouble("_adult_Fare"), rs.getDouble("_child_Fare"), rs.getDouble("_airport_Tax")));
            } while (rs.next());
            f = (Flight[]) flightArrayList.toArray();
            return f;
        } else {
            return null;
        }
    }
    
       /**
     * Method that creates an object according to its defined WHERE clause and
     * received variables
     *
     * @param _source The source city code  of the flight
     * @param _destination The destination code of the flight
     * @param _seats The amount of seats remaining needed
     * @throws SQLException
     *
     * @return f An array of Flight objects that contains the variables in the
     * Flight class
     */
    public static Flight[] getFlights(String _source, String _destination, int _seats) throws SQLException {
        ConnectionManager.init();
        ResultSet rs = ConnectionManager.selectAllColumns("Tbl_Flight_GroupNo", "source= '" + _source + "' and destination= '" + _destination + "'"); //falta poner que haya un mes de diferencia, no estoy seguro de como
        if (rs.next()) {
            ArrayList flightArrayList = new ArrayList();
            
            do {
                Flight temp = new Flight(rs.getInt("FlightNo"), rs.getString("AirlinesName"), City.getCity(rs.getString("Source")), City.getCity(rs.getString("Destination")), rs.getTime("DepartureTime"), rs.getTime("ArrivalTime"),
                        rs.getInt("TotalSeats"), rs.getDouble("AdultFare"), rs.getDouble("ChildFare"), rs.getDouble("AirportTax"));
                if(temp.getRemainingSeats() >= _seats){
                    flightArrayList.add(temp);
                }
            } while (rs.next());
            Flight f[] = new Flight[flightArrayList.size()];
            flightArrayList.toArray(f);
            return f;
        } else {
            return null;
        }
    }
    
    public int getRemainingSeats() throws SQLException{
        /* Sacar de DB la cantididad en base a this._flight_No */
        ConnectionManager.init();
        ResultSet rs = ConnectionManager.select("RemainingSeats", "Tbl_FlightSeat_Status_GroupNo", "FlightNo = '"+this._flight_No+"'");
        if(rs.next()){
            return rs.getInt("RemainingSeats");
        } else {
            return -1;
        }
    }
}