package core.hibernate;

import java.sql.Types;

import org.hibernate.dialect.SQLServerDialect;

/** 
 * This class implements our custom Hibernate Dialect that fixes the mapping of 
 * Hibernate Type BIGINT (default is NUMERIC(19,0)) to a SQL BIGINT 
 * 
 * @author Dominik Enkelmann, 25/february/2008 
 */ 
public class MSSQLDialect extends SQLServerDialect { 
    
	public MSSQLDialect() { 
		super(); 
		// Overwrite SQL Server datatype BIGINT
		registerColumnType(Types.BIGINT, "bigint");
	} 
}