package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        try {
            List<String> archivoEnArray = leerArchivo("input.in");
            //Obtengo la cantidad de passwords que hay en el archivo
            Integer numeroDePasswords = archivoEnArray.isEmpty() ? 0 : Integer.parseInt(archivoEnArray.get(0));
            //Pregunto si numero de passwords es distinto de 0
            if(! numeroDePasswords.equals(Integer.valueOf(0))) {
                //Remuevo el primer elemento que contiene la cantidad de elementos
                archivoEnArray.remove(0);
                //Llamo al algoritmo que resuelve todo
                realizarEjercicio(numeroDePasswords, archivoEnArray);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void realizarEjercicio(Integer numeroDePasswords, List<String> archivoEnArray) {
        //Ordeno las passwords
        Collections.sort(archivoEnArray);
        // Creo una lista de registros desde las passwords.
        // En si lo convierto en un objeto que cree.
        List<Registro> registros = archivoEnArray
                .stream()
                .map(Registro::new).collect(Collectors.toList());
        //Recorro los registros a partir de la posicion 1
        for(int i = 1; i < registros.size(); i++) {
            //Uso una bandera Boolean. Uso un wrapper del tipo primitivo boolean
            Boolean coincidePrimerLetra = true;
            //Seteo en j con i -1
            int j = i-1;
            //Recorro para atras los elementos
            while (coincidePrimerLetra && j >= 0) {
                coincidePrimerLetra=registros.get(i).coincidePrimerLetra(registros.get(j));
                if(registros.get(i).contieneOtraPassword(registros.get(j))) {
                    registros.get(i).agregarNivel();
                }
                j--;
            }
        }
        System.out.println(registros);
    }

    public static List<String> leerArchivo(String fileName) throws Exception {
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        List<String> passwords = new ArrayList<>();

        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            archivo = new File(fileName);
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);

            // Lectura del fichero
            System.out.println("Leyendo el contendio del" + fileName);
            String linea;
            while ((linea = br.readLine()) != null)
                passwords.add(linea);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // En el finally cerramos el fichero, para asegurarnos
            // que se cierra tanto si todo va bien como si salta
            // una excepcion.
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                throw new Exception("No se pudo abrir el archivo");
            }
        }
        return passwords;
    }

}
