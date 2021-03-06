package app.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
	private static Database instance;
	private String jdbcURL      = "jdbc:mysql://localhost:3306/kt_module?useSSl=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private String jdbcUsername = "root";
	private String jdbcPassword = "";

	private Connection connection;

	private Database(){

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch(ClassNotFoundException e) {
			System.out.println("driver mysql not found!");
		}

		System.out.println("MySQL connect: " + jdbcURL);

		try {
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
			System.out.println("MySQL connection successful.");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Stop Connect MySQL...");
		}
	}

	public static Database getInstance() {
		if (instance == null){
			synchronized (Database.class){
				instance = new Database();
			}
		}
		return instance;
	}

	public Connection getConnection() {
		return connection;
	}
}
