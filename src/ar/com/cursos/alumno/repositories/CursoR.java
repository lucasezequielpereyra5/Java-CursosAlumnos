package ar.com.cursos.alumno.repositories;

import ar.com.cursos.alumno.entities.Curso;
import ar.com.cursos.alumno.utils.Log;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CursoR {
    Connection conn;

    public CursoR(Connection conn) {
        this.conn = conn;
    }
    
    public void save(Curso curso){
        try {
            PreparedStatement ps=conn.prepareStatement(
               "insert into cursos (titulo,profesor,dia,turno) values "
                       + "(?,?,?,?)",1  
            );
            ps.setString(1, curso.getTitulo());
            ps.setString(2, curso.getProfesor());
            ps.setString(3, curso.getDia());
            ps.setString(4, curso.getTurno());
            ps.execute();
        } catch (Exception e) {
            Log.set(e);
        }
    }
    
    public List<Curso> getAll(){
        List<Curso>lista=new ArrayList();
        try {
            ResultSet rs=conn.createStatement()
                    .executeQuery("select * from cursos");
            while(rs.next()){
                lista.add(
                        new Curso(
                                rs.getInt("id"),
                                rs.getString("titulo"),
                                rs.getString("profesor"),
                                rs.getString("dia"),
                                rs.getString("turno")
                        )
                );
            }
        } catch (Exception e) {
            Log.set(e);
        }
        return lista;
    }
}
