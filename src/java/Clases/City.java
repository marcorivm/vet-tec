package Clases;

import dbcp.ConnectionManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class City {

	private String _cityCode;
	private String _cityName;

    public City(String _cityCode, String _cityName) {
        this._cityCode = _cityCode;
        this._cityName = _cityName;
    }

	public void setCityCode(String aCityCode) {
		this._cityCode = aCityCode;
	}

	public String getCityCode() {
		return this._cityCode;
	}

	public String getCityName() {
		return this._cityName;
	}

	public void setCityName(String aCityName) {
		this._cityName = aCityName;
	}
        
        
        /**
        * Method that creates an object according to its defined WHERE clause and
        * received variables
        *
        * @param _cityCode The code for the current city
        * @throws SQLException
        * @return ci An object returning the values of each City object created
        */
        public static City getCity(String _cityCode) throws SQLException {
            ResultSet rs = ConnectionManager.selectAllColumns("Tbl_City_GroupNo", "CityCode='" + _cityCode+"'");
            if (rs.next()) {
                City ci = new City(rs.getString("CityCode"), rs.getString("CityName"));
                return ci;
            } else {
                return null;    
            }
        }
        
    public static City[] getCities() throws SQLException {
            ConnectionManager.init();
            ResultSet rs = ConnectionManager.selectAllColumns("Tbl_City_GroupNo", "");
            City[] cts = null;
            ArrayList<City> ctsL = new ArrayList<City>();
            while(rs.next()){
                City ci = new City(rs.getString("CityCode"), rs.getString("CityName"));
                ctsL.add(ci);
            }
            if(ctsL.size()>0){
                cts = new City[ctsL.size()];
                ctsL.toArray(cts);
            }
            return cts;
    }
}