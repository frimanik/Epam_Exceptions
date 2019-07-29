package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Andrey_Vaganov on 12/5/2016.
 */
public class Main {

    /**
     * Формат даты
     */
    private static final String DATE_FORMAT_PATTERN = "dd.MM.yyyy";

    /**
     * Форматтер, используется для преобразования строк в даты и обратно
     */
    private static SimpleDateFormat dateFormatter = new SimpleDateFormat(DATE_FORMAT_PATTERN);


    /**
     * Точка входа в программу
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            readFile();
        } catch (ReadFileException e) {
            System.out.println("File not found");
        }
    }

    /**
     * Метод для чтения дат из файла
     */
    public static void readFile() throws ReadFileException {
        //Открываем потоки на чтение из файла
        try (FileReader reader = new FileReader("file.txt")) {
            BufferedReader byfReader = new BufferedReader(reader);

            //Читаем первую строку из файла
            String strDate = byfReader.readLine();

            while (strDate != null) {
                try {
                    //Преобразуем строку в дату
                    Date date = parseDate(strDate);

                    //Выводим дату в консоль в формате dd-mm-yy
                    System.out.printf("%1$td-%1$tm-%1$ty \n", date);
                } catch (ParseException exc) {
                    System.out.println("Incorrect form of data");
                } finally {
                    //Читаем следующую строку из файла
                    strDate = byfReader.readLine();
                }

            }
        } catch (IOException exc) {
            throw new ReadFileException("File not found", exc);
        }
    }

    /**
     * Метод преобразует строковое представление даты в класс Date
     *
     * @param strDate строковое представление даты
     * @return
     */
    public static Date parseDate(String strDate) throws ParseException {
        return dateFormatter.parse(strDate);
    }

}