package org.example;

import java.io.*;

public class FileUtils {
    public static Object loadObject(String fileName){
        Object returnObject;

        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(fileName));

            returnObject = objectInputStream.readObject();

            objectInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return returnObject;
    }

    // Spara objekt som fil
    public static void saveObject(Object objectToSave, String fileName){
        try {
            FileOutputStream outputStream = new FileOutputStream(fileName);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

            objectOutputStream.writeObject(objectToSave);

            objectOutputStream.close();
            outputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }


    // Läsa textfil
    public static String readTextFile(String fileName){
        StringBuilder returnString = new StringBuilder();

        try {
            FileInputStream inputStream = new FileInputStream(fileName);
            InputStreamReader reader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(reader);

            String line;
            while ((line = bufferedReader.readLine()) != null){
                returnString.append(line);
            }

            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return returnString.toString();
    }

    public static void writeTextFile(String text, String fileName){

        try {
            FileOutputStream outputStream = new FileOutputStream(fileName);
            OutputStreamWriter writer = new OutputStreamWriter(outputStream);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            bufferedWriter.write(text);
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
            // throw new RuntimeException(e);
        }


    }
}
