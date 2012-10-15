package Clases;

import dbcp.ConnectionManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Date;

public class FlightSeat_Status {

    private Flight _flightNumber;
    private Date _dateOfJourney;
    private int _remainingSeats;
    private Time _timeOfJourney;

    public FlightSeat_Status(Flight _flightNumber, Date _dateOfJourney, int _remainingSeats, Time _timeOfJourney) {
        this._flightNumber = _flightNumber;
        this._dateOfJourney = _dateOfJourney;
        this._remainingSeats = _remainingSeats;
        this._timeOfJourney = _timeOfJourney;
    }

    

    public Flight getFlightNumber() {
        return this._flightNumber;
    }

    public void setFlightNumber(Flight aFlightNumber) {
        this._flightNumber = aFlightNumber;
    }

    public Date getDateOfJourney() {
        return this._dateOfJourney;
    }

    public void setDateOfJourney(Date aDateOfJourney) {
        this._dateOfJourney = aDateOfJourney;
    }

    public int getRemainingSeats() {
        return this._remainingSeats;
    }

    public void setRemainingSeats(int aRemainingSeats) {
        this._remainingSeats = aRemainingSeats;
    }

    public Time getTimeOfJourney() {
        return this._timeOfJourney;
    }

    public void setTimeOfJourney(Time aTimeOfJourney) {
        this._timeOfJourney = aTimeOfJourney;
    }

    /**
     * Method that creates an object with the according information of the
     * FlightSeat_Status java class
     *
     * @param id The Flight's ID
     * @throws SQLException
     *
     * @return fst An object that contains the information of the each flight
     * seat
     */
    public static FlightSeat_Status getFlightSeat_Status(int id) throws SQLException {
        ResultSet rs = ConnectionManager.selectAllColumns("Tbl_FlightSeat_Status_GroupNo", "FlightNo= " + id);
        if (rs.next()) {
            FlightSeat_Status fst = new FlightSeat_Status(Flight.getFlight(rs.getInt("FlightNo")), rs.getDate("DateOfJourney"), rs.getInt("RemainingSeats"), rs.getTime("DateOfJourney"));
            return fst;
        } else {
            return null;
        }
    }
}