package ar.com.cursos.alumno.repositories;

import ar.com.cursos.alumno.entities.Alumno;
import ar.com.cursos.alumno.utils.Log;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AlumnoR {
    private Connection conn;

    public AlumnoR(Connection conn) {
        this.conn = conn;
    }
    
    public void save(Alumno a){
        //String query="insert into alumnos (nombre,apellido,edad,curso) values "
        //        + "('"+a.getNombre()+"','"+a.getApellido()+"',"+a.getEdad()+","+
        //        a.getCurso()+")";
        try{
            PreparedStatement ps=conn.prepareStatement(
                "insert into alumnos (nombre,apellido,edad,curso) values (?,?,?,?)",
                PreparedStatement.RETURN_GENERATED_KEYS);
                ps.setString(1, a.getNombre());
                ps.setString(2, a.getApellido());
                ps.setInt(3, a.getEdad());
                ps.setInt(4, a.getCurso());
            ps.execute();
            ResultSet rs=ps.getGeneratedKeys();
            if(rs.next()) a.setId(rs.getInt(1));
        }catch(Exception e){
            Log.set(e);
        }
    }
    
    private List<Alumno> getByFiltro(String filtro){
        //select * from alumnos where 1=1;
        //select * from alumnos where apellido='perez'
        String query="select * from alumnos where "+filtro;
        List<Alumno> lista=new ArrayList();
        try{
            ResultSet rs=conn.createStatement().executeQuery(query);
            while(rs.next()){
                lista.add(new Alumno(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getInt("edad"),
                        rs.getInt("curso")
                ));
            }
        }catch(Exception e){
            Log.set(e);
        }
        
        return lista;
    }
    
    public List<Alumno> getAll(){
        return getByFiltro("1=1");
    }
    
    public List<Alumno> getByApellido(String apellido){
        return getByFiltro("apellido='"+apellido+"'");
    }
    
    public List<Alumno> getByCurso(int curso){
        return getByFiltro("curso="+curso);
    }
    
    public Alumno getById(int id){
        List<Alumno>lista=getByFiltro("id="+id);
        if(lista.isEmpty()) return null;
        return lista.get(0);
    }
            
}