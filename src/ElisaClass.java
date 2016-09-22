import java.util.*;
import java.util.Random;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class ElisaClass 
{
	public HashMap<String,String> replacementMap = new HashMap<String,String>();
	@SuppressWarnings("resource")
	public void addData(String resp)
	{
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		Random rnd = new Random();

		int randomHQ = 1 + rnd.nextInt(2);
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//con = DriverManager.getConnection("jdbc:oracle:thin:sys as sysdba/oracle@localhost:1521:orcl");

			con = DriverManager.getConnection("jdbc:oracle:thin:ora1/ora1@localhost:1521:orcl");
			stmt = con.createStatement();
			String sql ="SELECT * FROM (SELECT Description FROM   elisa WHERE Type = 'h' ORDER BY DBMS_RANDOM.RANDOM)	WHERE  rownum=1";
			String sql1 ="SELECT * FROM (SELECT Description FROM   elisa WHERE Type = 'q' ORDER BY DBMS_RANDOM.RANDOM)	WHERE  rownum=1";
			rs = stmt.executeQuery(sql);
		
			replacementMap.put("i", "you");
			replacementMap.put("me", "you");
			replacementMap.put("my", "your");
			replacementMap.put("am", "are");


			while(rs.next())
			{
				String myResponse = rs.getString(1);
				System.out.println(myResponse);
				
				rs1 = stmt.executeQuery(sql1);	
				while(rs1.next())
				{
					
					String myResponse1 = rs1.getString(1);
					String myResponse2 = null;
					System.out.print(myResponse1);
					String delim = " ";
					String[] parts = resp.split(delim);

					for(int i=0;i<parts.length;i++)
					{
						if(replacementMap.containsKey(parts[i]))
						{
							myResponse2 = replacementMap.get(parts[i]);
							System.out.print(" " +myResponse2);
						}
						else
						{
							System.out.print(" " +parts[i]);
						}

					}	
				}

			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}

		}
	}
}