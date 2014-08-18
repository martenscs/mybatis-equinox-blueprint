package net.martenscs.osgi.derby.db.init;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

import javax.sql.DataSource;

public class SampleDatabaseInit {

	private static final Logger LOG = null;

	private DataSource datasource;

	public SampleDatabaseInit() {

	}

	public void create() throws Exception {
		
		String sql = "create table CATEGORY (\n"
				+ "  category_id integer primary key,\n" 
				+ "  category_name varchar(45))";
		
			
		//LOG.info("Creating table orders ...");

		try {
			execute("drop table CATEGORY");
		} catch (Throwable e) {
			// ignore
		}

		execute(sql);
		sql = "INSERT INTO CATEGORY VALUES (1,'Apparel')";
		execute(sql);
		//LOG.info("... created table orders");
	}

	public void destroy() throws Exception {
		try {
			execute("drop table CATEGORY");
		} catch (Throwable e) {

		}
	}

	private void execute(String sql) throws SQLException {

		Connection con = getDatasource().getConnection();
		Statement stm = con.createStatement();
		stm.execute(sql);
		// must commit connection
		con.commit();
		stm.close();
		con.close();

	}

	public DataSource getDatasource() {
		return datasource;
	}

	public void setDatasource(DataSource datasource) {
		this.datasource = datasource;
	}
}
