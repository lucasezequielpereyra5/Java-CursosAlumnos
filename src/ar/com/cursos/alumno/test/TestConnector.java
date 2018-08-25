package ar.com.cursos.alumno.test;

import ar.com.cursos.alumno.connector.Connector;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestConnector {
    public static void main(String[] args) throws Exception{
        String query="insert into alumnos (nombre,apellido,edad,curso) values "
                + "('juan','perez',25,1)";
        Connection conn=Connector.getConnection();
        Statement st=conn.createStatement();
        st.execute(query);
        
        Connector.getConnection().createStatement().execute(
            "insert into alumnos (nombre,apellido,edad,curso) values "
                    + "('ana','sosa',34,1)"
        );
        
        ResultSet rs=Connector.getConnection().createStatement()
                .executeQuery("select * from alumnos");
        while(rs.next()){
            System.out.println(
                    rs.getInt("id")+" "+
                    rs.getString("nombre")+" "+
                    rs.getString("apellido")+" "+
                    rs.getInt("edad")+" "+
                    rs.getInt("curso")
            );
        }
        
    }
}