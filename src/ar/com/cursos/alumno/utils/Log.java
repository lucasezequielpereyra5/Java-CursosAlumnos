package ar.com.cursos.alumno.utils;

import java.net.InetAddress;
import java.time.LocalDateTime;
import java.util.Arrays;

public class Log {
    private static final String file="log.csv";
    
    public static void set(Exception e){
        LocalDateTime ldt=LocalDateTime.now();
        String user=System.getProperty("user.name");
        InetAddress net=null;
        try { net=InetAddress.getLocalHost(); } catch (Exception ex) { System.out.println(ex); }
        String registro=ldt+";"+user+";"+net+";"+e+";"
                +Arrays.toString(e.getStackTrace());
        new FileUtil(file).addLine(registro);
        System.out.println("ocurrio un error.");
    }
}
