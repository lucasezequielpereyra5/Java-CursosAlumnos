package ar.com.cursos.alumno.test;

import ar.com.cursos.alumno.connector.Connector;
import ar.com.cursos.alumno.entities.Alumno;
import ar.com.cursos.alumno.repositories.AlumnoR;
import java.sql.Connection;

public class TestRepository {
    public static void main(String[] args) {
        Connection conn=Connector.getConnection();
        AlumnoR ar=new AlumnoR(conn);
        Alumno alumno=new Alumno("diego","soto",22,2);
        ar.save(alumno);
        System.out.println(alumno);
        System.out.println("-----------------------");
        //ar.getAll().forEach(System.out::println);
        ar.getByApellido("soto").forEach(System.out::println);
        
    }
}