package com.epam.lab.jdbc.homework.metadata;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import com.epam.lab.jdbc.homework.manage.connection.ConnectionToDB;
import com.epam.lab.jdbc.homework.manage.connection.Credentials;

public class MetadataReader {

	private static Scanner scanner;
	private static final String format = "%-40s";

	public static void startUI() {
		boolean isWorking = true;
		scanner = new Scanner(System.in);
		while (isWorking) {
			System.out.println("Choose operation:");
			System.out.println("1 - get schemas");
			System.out.println("2 - get tables");
			System.out.println("3 - get columns of table");
			System.out.println("4 - get info from table");
			System.out.println("0 - exit");
			int input = scanner.nextInt();
			switch (input) {
			case 1:
				getSchemasUI();
				break;
			case 2:
				getTablesUI();
				break;
			case 3:
				getColumnsUI();
				break;
			case 4:
				getInfoUI();
				break;
			case 0:
				isWorking = false;
				break;
			}
			System.out.println("------------------------------------------------");
		}
		scanner.close();
	}

	public static void getSchemasUI() {
		System.out.println("Schemas of current database are:");
		getSchemas().forEach(System.out::println);
	}

	private static List<String> getSchemas() {
		List<String> result = new LinkedList<>();
		try (Connection connection = ConnectionToDB.getNewConnection()) {
			DatabaseMetaData metadata = connection.getMetaData();
			ResultSet rs = metadata.getCatalogs();
			while (rs.next()) {
				String schemaName = rs.getString(1);
				result.add(schemaName);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public static void getTablesUI() {
		System.out.print("Enter name of the schema to read tables from: ");
		String input = scanner.next();
		System.out.println();
		System.out.println("Tables of schema " + input + " are:");
		getSchemaTables(input).forEach(System.out::println);
	}

	private static List<String> getSchemaTables(String schemaName) {
		List<String> result = new LinkedList<>();
		String[] types = { "TABLE" };
		String newURL = Credentials.URL + "/" + schemaName;
		try (Connection connection = DriverManager.getConnection(newURL, Credentials.USERNAME, Credentials.PASSWORD)) {
			DatabaseMetaData metadata = connection.getMetaData();
			ResultSet rs = metadata.getTables(null, null, "%", types);
			while (rs.next()) {
				String tableName = rs.getString(3);
				result.add(tableName);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public static void getColumnsUI() {
		System.out.print("Enter name of the schema to read tables from: ");
		String schema = scanner.next();
		System.out.println();
		System.out.print("Enter name of the table to read columns from:");
		String table = scanner.next();
		System.out.println();
		System.out.println("Columns of table " + schema + "." + table + " are: ");
		getTableColumns(schema, table).forEach(s -> System.out.printf(format, s));
		System.out.println();
	}

	private static List<String> getTableColumns(String schemaName, String tableName) {
		List<String> result = new LinkedList<>();
		String newURL = Credentials.URL + "/" + schemaName;
		try (Connection connection = DriverManager.getConnection(newURL, Credentials.USERNAME, Credentials.PASSWORD)) {
			Statement statement = connection.createStatement();
			String query = "SELECT * FROM " + tableName;
			ResultSet rs = statement.executeQuery(query);
			ResultSetMetaData rsmd = rs.getMetaData();
			int columns = rsmd.getColumnCount();
			for (int i = 1; i <= columns; i++) {
				result.add(rsmd.getColumnName(i) + "(" + rsmd.getColumnTypeName(i) + ")");
			}
			rs.close();
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public static void getInfoUI() {
		System.out.print("Enter name of the schema to read tables from: ");
		String schema = scanner.next();
		System.out.println();
		System.out.print("Enter name of the table to read info from:");
		String table = scanner.next();
		System.out.println();
		System.out.println("Info in table " + schema + "." + table + " is: ");
		getTableColumns(schema, table).forEach(s -> System.out.printf(format, s));
		System.out.println();
		getTableInfo(schema, table).forEach(System.out::println);
	}

	private static List<String> getTableInfo(String schemaName, String tableName) {
		List<String> result = new LinkedList<>();
		String newURL = Credentials.URL + "/" + schemaName;
		try (Connection connection = DriverManager.getConnection(newURL, Credentials.USERNAME, Credentials.PASSWORD)) {
			Statement statement = connection.createStatement();
			String query = "SELECT * FROM " + tableName;
			ResultSet rs = statement.executeQuery(query);
			ResultSetMetaData rsmd = rs.getMetaData();
			int columns = rsmd.getColumnCount();
			String info = null;
			while (rs.next()) {
				info = "";
				for (int i = 1; i <= columns; i++) {
					info += String.format(format, rs.getString(i));
				}
				result.add(info);
			}
			rs.close();
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
