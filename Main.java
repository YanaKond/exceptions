import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Введите данные в формате: Фамилия Имя Отчество дата_рождения номер_телефона пол");

            String input = scanner.nextLine();
            String[] inputData = input.split(" ");

            if (inputData.length != 6) {
                System.err.println("Неверное количество данных. Правильный формат: Фамилия Имя Отчество дата_рождения номер_телефона пол");
                return;
            }

            String surname = inputData[0];

            try {
                LocalDate.parse(inputData[3], DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            } catch (Exception e) {
                System.err.println("Неверный формат даты рождения. Правильный формат: dd.MM.yyyy");
                return;
            }

            try {
                Long.parseLong(inputData[4]);
            } catch (NumberFormatException e) {
                System.err.println("Неверный формат номера телефона. Правильный формат: целое беззнаковое число без форматирования");
                return;
            }

            String gender = inputData[5];
            if (!gender.equals("m") && !gender.equals("f")) {
                System.err.println("Неверный формат пола. Правильный формат: m или f");
                return;
            }

            String record = surname + " " + inputData[1] + " " + inputData[2] + " " + inputData[3] + " " + inputData[4] + " " + inputData[5];

            try (FileWriter fw = new FileWriter(surname + ".txt", true)) {
                fw.write(record);
                fw.write("\n");
                System.out.println("Данные успешно записаны в файл.");
            } catch (IOException e) {
                System.err.println("Ошибка при записи в файл: " + e.getMessage());
            }
        }
    }
}
