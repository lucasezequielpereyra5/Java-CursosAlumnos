package ar.com.cursos.alumno.gui;

import ar.com.cursos.alumno.connector.Connector;
import ar.com.cursos.alumno.entities.Alumno;
import ar.com.cursos.alumno.entities.Curso;
import ar.com.cursos.alumno.repositories.AlumnoR;
import ar.com.cursos.alumno.repositories.CursoR;
import ar.com.cursos.alumno.utils.FxTable;
import ar.com.cursos.alumno.utils.Validator;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class FXMLDocumentController implements Initializable {

    private Connection conn;
    private AlumnoR ar;
    private Alumno alumno;
    private CursoR cr;
    private Curso curso;
    
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtApellido;
    @FXML
    private TextField txtEdad;
    private TextField txtCurso;
    @FXML
    private Label lblInfo;
    @FXML
    private TableView<Alumno> tblAlumnos;
    @FXML
    private TextField txtTitulo;
    @FXML
    private TextField txtProfesor;
    @FXML
    private ComboBox<String> cmbDia;
    @FXML
    private ComboBox<String> cmbTurno;
    @FXML
    private Label lblInfoCursos;
    @FXML
    private TableView<Curso> tblCursos;
    @FXML
    private ComboBox<Curso> cmbCursos;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conn=Connector.getConnection();
        ar=new AlumnoR(conn);
        cr=new CursoR(conn);
        cargar();
        
        //cargar cmbDia
        cmbDia.getItems().addAll("Lunes","Martes","Miércoles","Jueves","Viernes");
        cmbDia.setValue("Lunes");
        
        //cargar cmbTurnos
        cmbTurno.getItems().addAll("Mañana","Tarde","Noche");
        cmbTurno.setValue("Noche");
        
    }    

    private void cargar(){
        
        //cargar tblAlumnos
        new FxTable().cargar(ar.getAll(), tblAlumnos);
        
        //cargar tblCursos
        new FxTable().cargar(cr.getAll(), tblCursos);
        
        //cargar cmbCursos
        cmbCursos.getItems().addAll(cr.getAll());
    }
    
    
    @FXML
    private void agregar(ActionEvent event) {
        if(validar()){
            alumno=new Alumno(
                    txtNombre.getText(),
                    txtApellido.getText(),
                    Integer.parseInt(txtEdad.getText()),
                    cmbCursos.getValue().getId()
            );
            ar.save(alumno);
            lblInfo.setText("Se ingreso el alumno id: "+alumno.getId());
            limpiar();
        }else{
            lblInfo.setText("No se pudo ingresar el alumno.");
        }  
        cargar();
    }
    
    private void limpiar(){
        txtNombre.setText("");
        txtApellido.setText("");
        txtEdad.setText("");
        //txtCurso.setText("");
        txtNombre.requestFocus();
    }
    
    private boolean validar(){
        if(!new Validator(txtNombre).isSize(2, 20)) return false;
        if(!new Validator(txtApellido).isSize(2, 20)) return false;
        if(!new Validator(txtEdad).isInteger(18, 90)) return false;
        return true;
    }

    @FXML
    private void agregarCurso(ActionEvent event) {
        Curso curso=new Curso(
                txtTitulo.getText(),
                txtProfesor.getText(),
                cmbDia.getValue(),
                cmbTurno.getValue()
        );
        cr.save(curso);
        lblInfoCursos.setText("Se Agrego un Curso.");
        cargar();
    }
}