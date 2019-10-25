package iam46258177;

import java.io.*;

public class LeerFichObject {
    public static void main(String[] args) throws IOException, ClassNotFoundException{
        Persona persona;   //defino la variable persona
        File fichero = new File("FichData.dat");//declara el fichero
        FileInputStream filein = new FileInputStream(fichero);//crea el flujo de entrada
        //conecta el flujo de bytes al flujo de datos
        DataInputStream dataIS = new DataInputStream(filein);
        //ObjectInputStream objectInputStream = new ObjectInputStream(filein);
        int i=1;
        try {
            for (;;) { //lectura del fichero
                //persona= (Persona) dataIS.readObject(); //leer una Persona
                //persona = (Persona) objectInputStream.readObject();
                //System.out.println("Nombre: " + persona.getNombre() + ", Edad: " + persona.getEdad());
                //System.out.println("Nombre: " + objectInputStream.readObject() );
                System.out.println("Nombre: " + dataIS.readUTF() + ", edad: " + dataIS.readInt());
                //System.out.println(i);i++;
            }
        }catch (EOFException eo) {}
        catch (StreamCorruptedException x) {}
        dataIS.close();  //cerrar stream de entrada
    }
}
