import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;


public class Controlador{
    
    Scanner scT = new Scanner(System.in);
    Scanner scInt = new Scanner(System.in);

    File archivo = null;
    FileReader fr = null;
    BufferedReader br = null;
    FileWriter fichero = null;


    BinarySearchTree treeEnglish = new BinarySearchTree();
    BinarySearchTree treeEspañol = new BinarySearchTree();
    BinarySearchTree treeFrances = new BinarySearchTree();

    public void leerArchivo(){

        try{
            System.out.println("Ingrese el nombre del archivo a leer: ");
            String src = scT.nextLine();
            archivo = new File(src);
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
        } catch(Exception e){
            System.out.println("Error! Archivo no econtrado");
        }

        try{
            String linea = "";

            while((linea = br.readLine()) != null){

                String[] palabras = linea.toLowerCase().split(",");

                treeEnglish.insert(palabras[0], palabras);

                treeEspañol.insert(palabras[0], palabras);

                treeFrances.insert(palabras[0], palabras);

            }
            System.out.println("Se ha leído el archivo correctmente!");
        } catch (Exception e){
            System.out.println("No se ha podido leer el archivo");
        }
    }

    public void verDatos(){
        System.out.println("-->Datos en el archivo<--");

        System.out.println("1. Español");
        System.out.println("2. Ingles");
        System.out.println("3. Frances");

        int opcion = scInt.nextInt();

        if (opcion == 1){
            System.out.println("Datos en español");
            treeEspañol.getBST();
        } else if (opcion == 2){
            System.out.println("Datos en ingles");
            treeEnglish.getBST();
        } else if (opcion == 3){
            System.out.println("Datos en frances");
            treeFrances.getBST();
        }
    }

    public void traducir(){

        System.out.println("Traducir");
        System.out.println("Ingrese el lenguaje de entrada");
        System.out.println("1. Español");
        System.out.println("2. Ingles");
        System.out.println("3. Frances");
        int lenguaje = scInt.nextInt();

        System.out.println("Ingrese el lenguaje de salida");
        System.out.println("1. Español");
        System.out.println("2. Ingles");
        System.out.println("3. Frances");
        int traductor = scInt.nextInt();

        System.out.println("Ingrese el la ruta del archivo a traducir");
        String src2 = scT.nextLine();

        try{
            archivo = new File(src2);
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
        } catch (Exception e){
            System.out.println("No se encontro el archivo!");
        }

        try{
            String linea;
            String traductorr = " ";

            while((linea = br.readLine()) != null){
                String[] palabra = linea.split(" ");

                for (int i = 0; i < palabra.length; i++){
                    String[] diccionario = null;

                    if (lenguaje == 1){
                        diccionario = treeEspañol.getNode(palabra[i].toLowerCase());
                    } else if (lenguaje == 2){
                        diccionario = treeEnglish.getNode(palabra[i].toLowerCase());
                    } else if (lenguaje == 3){
                        diccionario = treeFrances.getNode(palabra[i].toLowerCase());
                    }

                    if (diccionario == null){
                        traductorr += "*" + palabra[i] + "*";
                    } else {
                        traductorr += " " + diccionario[traductor - 1] + " ";
                    }
                }
            }

            System.out.println(traductorr);
        } catch (Exception e){
            System.out.println("Error");
        }


    }

    public void correr(){

        Boolean correr = true;

        while(correr == true){

            System.out.println("--> Diccionario <--");
            System.out.println("1. Ver datos");
            System.out.println("2. Traducir texto");
            System.out.println("3. Salir");

            int opcion = scInt.nextInt();
            
            try{
                if (opcion == 1){
                    verDatos();
                } else if (opcion == 2){
                    traducir();
                } else if (opcion == 3){
                    correr = false;
                }
            } catch (Exception e){
                System.out.println("Ingrese una opcion valida!");
            }

        }

    }
}