import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

/**
 * 
 *  @description Clase que establece una conexión a BD utilizando la clase DriverManager. lee los datos de u archivo de propiedades
 *	@author Laura
 *  @date 26/3/2015
 *  @version 1.0
 *  @license GPLv3
 */
public class ConexionBD {

	public String dbms;
	public String dbName;
	public String userName;
	public String password;
	public String urlString;

	private String serverName;
	private int portNumber;
	private Properties prop;

	public ConexionBD(String propertiesFileName) {
		super();
		try {
			this.setProperties(propertiesFileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void setProperties(String fileName) throws IOException, InvalidPropertiesFormatException {
		this.prop = new Properties();
	    prop.loadFromXML(Files.newInputStream(Paths.get(fileName)));

		this.dbms = this.prop.getProperty("dbms");
		this.dbName = this.prop.getProperty("database_name");
		this.userName = this.prop.getProperty("user_name");
		this.password = this.prop.getProperty("password");
		this.serverName = this.prop.getProperty("server_name");
		this.portNumber = Integer
				.parseInt(this.prop.getProperty("port_number"));
	}
	public Connection getConnection() throws SQLException {

		Connection conn = null;
		Properties connectionProps = new Properties();
		connectionProps.put("user", this.userName);
		connectionProps.put("password", this.password);
			conn = DriverManager.getConnection("jdbc:" + this.dbms + "://"
					+ this.serverName + ":" + this.portNumber + "/" + this.dbName,
					connectionProps);
		return conn;
	}

	public static void closeConnection(Connection connArg) {
		try {
			if (connArg != null) {
				connArg.close();
				connArg = null;
			}
		} catch (SQLException sqle) {
			System.err.println(sqle);
		}
	}
	
	
}