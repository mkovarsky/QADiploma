package data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateGenerator {
    static Faker faker = new Faker();
    private LocalDate today = LocalDate.now();
    private DateTimeFormatter formatterYears = DateTimeFormatter.ofPattern("yy");
    private DateTimeFormatter formatterMonth = DateTimeFormatter.ofPattern("MM");

    @Value
    public static class Year {
        private String year;
    }

    public Year futureYear() {
        LocalDate newDate = today.plusYears(5);
        return new Year(formatterYears.format(newDate));
    }

    public Year pastYear() {
        LocalDate newDate = today.minusYears(2);
        return new Year(formatterYears.format(newDate));
    }

    public Year currentYear() {
        LocalDate newDate = today;
        return new Year(formatterYears.format(newDate));
    }

    public Year wrongYear() {
        return new Year(Integer.toString(faker.number().numberBetween(88, 99)));
    }

    @Value
    public static class Month {
        private String month;
    }

    public Month futureMonth() {
        LocalDate newDate = today.plusMonths(1);
        return new Month(formatterMonth.format(newDate));
    }

    public Month pastMonth() {
        LocalDate newDate = today.minusMonths(2);
        return new Month(formatterMonth.format(newDate));
    }

    public Month currentMonth() {
        LocalDate newDate = today;
        return new Month(formatterYears.format(newDate));
    }

    public Month wrongMonth() {
        return new Month(Integer.toString(faker.number().numberBetween(13, 99)));
    }

}

