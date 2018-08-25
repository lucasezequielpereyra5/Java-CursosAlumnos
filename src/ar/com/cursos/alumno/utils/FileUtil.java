package ar.com.cursos.alumno.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileUtil {

    private File file;
    
    public FileUtil(File file) {
        this.file = file;
    }
    
    public FileUtil(String file) {
        this.file = new File(file);
    }
    
    public void print() {        
        int car;
        try {
            FileReader in = new FileReader(file);
            while ((car = in.read()) != -1) {
                System.out.print((char) car);
            }
            //in.close();
        } catch (FileNotFoundException e) {
            System.out.println("No se encontro el archivo.");
        } catch (IOException e) {
            System.out.println("Ocurrio un error al leer el archivo.");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public String getText() {
        //String texto="";
        StringBuilder sb = new StringBuilder();
        int car;
        try {
            FileReader in = new FileReader(file);
            while ((car = in.read()) != -1) {
                sb.append((char) car);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return sb.toString();
    }
    
    public void setText(String text) {
        try {
            FileWriter out = new FileWriter(file);
            out.write(text);
            out.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void append(String text) {
        try {
            FileWriter out = new FileWriter(file, true);
            out.write(text);
            out.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void addLine(String line) {
        //usamos NIO
        try {
            BufferedWriter out = Files.newBufferedWriter(Paths.get(file.toURI()),
                    charset, StandardOpenOption.APPEND);
            out.write(line + "\n");
            out.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    private BufferedReader input;

    public void open() {
        try {
            input = new BufferedReader(new FileReader(file));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public String getLine() {
        try {
            return input.readLine();
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public void close() {
        try {
            input.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //private Charset charset=Charset.forName("UTF-8");
    private Charset charset = Charset
            .forName(StandardCharsets.UTF_8.toString());

    public List<String> getLines() {
        List<String> lineas = new ArrayList();
        //usamos paquete NIO
        Path archivo = Paths.get(file.toURI());
        try {
            lineas = Files.readAllLines(archivo, charset);
        } catch (IOException ex) {
            Log.set(ex);
        }
        return lineas;
    }
    
    public Set<String> getLinkedHashSetLines() {
        Set<String> setLines = new LinkedHashSet();
        setLines.addAll(getLines());
        return setLines;
    }
    
    public Set<String> getTreeSetLines() {
        Set<String> setLines = new TreeSet();
        setLines.addAll(getLines());
        return setLines;
    }
    
    public void setLines(List<String> lista) {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(file));
            for (String st : lista) {
                out.write(st + "\n");
            }
            out.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void remove(String line) {
        List<String> lista = getLines();
        lista.remove(line);
        setLines(lista);
    }
}