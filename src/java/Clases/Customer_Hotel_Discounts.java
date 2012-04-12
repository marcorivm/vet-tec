package Clases;

    import dbcp.ConnectionManager;
    import java.sql.ResultSet;
    import java.sql.Time;
    import java.util.Date;
    import java.sql.SQLException;

    public class Customer_Hotel_Discounts {

        private Hotel _hotel_ID;
        private Customer _customerId;
        private Date _dateOfBooking;
        private Time _timeOfBooking;

        public Customer_Hotel_Discounts(Hotel _hotel_ID, Customer _customerId, Date _dateOfBooking, Time _timeOfBooking) {
            this._hotel_ID = _hotel_ID;
            this._customerId = _customerId;
            this._dateOfBooking = _dateOfBooking;
            this._timeOfBooking = _timeOfBooking;
        }

        public Hotel getHotel_ID() {
            return this._hotel_ID;
        }

        public void setHotel_ID(Hotel aHotel_ID) {
            this._hotel_ID = aHotel_ID;
        }

        public Customer getCustomerId() {
            return this._customerId;
        }

        public void setCustomerId(Customer aCustomerId) {
            this._customerId = aCustomerId;
        }

        public Date getDateOfBooking() {
            return this._dateOfBooking;
        }

        public void setDateOfBooking(Date aDateOfBooking) {
            this._dateOfBooking = aDateOfBooking;
        }

        public Time getTimeOfBooking() {
            return this._timeOfBooking;
        }

        public void setTimeOfBooking(Time aTimeOfBooking) {
            this._timeOfBooking = aTimeOfBooking;
        }

        /**
        * Method that creates an object according to its defined WHERE clause and
        * received variables
        *
        * @param idh The hotel's ID
        * @param idc The Customer's ID
        * @throws SQLException
        * @return chd An object created out of the variables of the
        * Customer_Hotel_Discount class
        */
        public static Customer_Hotel_Discounts getCustomer_Hotel_Discounts(Hotel idh, Customer idc) throws SQLException {
            ResultSet rs = ConnectionManager.selectAllColumns("Customer_Hotel_Discounts", "_hotelID= " + idh + "_customerID= " + idc);
            if (rs.next()) {
                Customer_Hotel_Discounts chd = new Customer_Hotel_Discounts(Hotel.getHotel(rs.getString("_hotel_ID")), Customer.getCustomer(rs.getInt("_customerId")), rs.getDate("_dateOfBooking"), rs.getTime("_timeOfBooking"));
                return chd;
            } else {
                return null;
            }
        }
    }