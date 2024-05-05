package net.bushfire.crud.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.bushfire.crud.model.Drones;

public class DronesDAO {
	private String jdbcURL = "jdbc:mysql://localhost:3306/bushfire?useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "cocacola";
	
	
	private static final String INSERT_DRONES_SQL = "INSERT INTO drones" + "  (dname, dcoordinates, dmodel) VALUES "
			+ " (?, ?, ?);";

	private static final String SELECT_DRONES_BY_ID = "select id,dname,dcoordinates,dmodel from drones where id =?";
	private static final String SELECT_ALL_DRONES = "select * from drones";
	private static final String DELETE_DRONES_SQL = "delete from drones where id = ?;";
	private static final String UPDATE_DRONES_SQL = "update drones set dname = ?,dcoordinates= ?, dmodel =? where id = ?;";


	public DronesDAO() {
	}

	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
	
	
	public void insertDrones(Drones drones) throws SQLException {
		System.out.println(INSERT_DRONES_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_DRONES_SQL)) {
			preparedStatement.setString(1, drones.getDname());
			preparedStatement.setString(2, drones.getDcoordinates());
			preparedStatement.setString(3, drones.getDmodel());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	
	public boolean updateDrones(Drones drones) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_DRONES_SQL);) {
			statement.setString(1, drones.getDname());
			statement.setString(2, drones.getDcoordinates());
			statement.setString(3, drones.getDmodel());
			statement.setInt(4, drones.getId());

			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}
	
	
	public Drones selectDrones(int id) {
		Drones drones = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DRONES_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String dname = rs.getString("dname");
				String dcoordinates = rs.getString("dcoordinates");
				String dmodel = rs.getString("dmodel");
				drones = new Drones(id, dname, dcoordinates, dmodel);
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		return drones;
	}
	
	
	public List<Drones> selectAllDrones() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Drones> drones = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_DRONES);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("dname");
				String email = rs.getString("dcoordinates");
				String country = rs.getString("dmodel");
				drones.add(new Drones(id, name, email, country));
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		return drones;
	}
	
	
	public boolean deleteDrones(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_DRONES_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

}
