package iam46258177;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import java.io.*;

public class CrearPersonaXml {
    public static void main(String argv[]) throws IOException{
        File fichero = new File("FichData.dat");
        Persona persona;

        FileInputStream filein = new FileInputStream(fichero);
        //DataInputStream dataIS = new DataInputStream(filein);
        ObjectInputStream objectInputStream = new ObjectInputStream(filein);

        int  edad, posicion=0; //para situarnos al principio del fichero
        String nombre;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try{
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation implementation = builder.getDOMImplementation();
            Document document = implementation.createDocument(null, "Personas", null);
            document.setXmlVersion("1.0"); // asignamos la version de nuestro XML

            while (filein.available()>0) {
                persona = (Persona) objectInputStream.readObject();
                nombre = persona.getNombre(); // obtengo id de empleado
                edad = persona.getEdad();

                Element raiz = document.createElement("persona"); //nodo empleado
                document.getDocumentElement().appendChild(raiz);

                CrearElemento("nombre",nombre.trim(), raiz, document);
                CrearElemento("edad",Integer.toString(edad), raiz, document); //a�adir ID

            }

            //del for que recorre el fichero

            Source source = new DOMSource(document);
            Result result = new StreamResult(new java.io.File("Persona.xml"));
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);
            Result console= new StreamResult(System.out);
            transformer.transform(source, console);


        }catch(Exception e){System.err.println("Error: "+e);}
        //dataIS.close();  //cerrar fichero
        objectInputStream.close();
    }//de main

    //Inserci�n de los datos del empleado
    static void  CrearElemento(String datoEmple, String valor,
                               Element raiz, Document document){
        Element elem = document.createElement(datoEmple); //creamos hijo
        Text text = document.createTextNode(valor); //damos valor
        raiz.appendChild(elem); //pegamos el elemento hijo a la raiz
        elem.appendChild(text); //pegamos el valor
    }
}//de la clase