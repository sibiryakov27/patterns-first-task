package ru.netology.util;

import com.github.javafaker.Faker;
import ru.netology.util.data.CardOrderTestDataWithReplanningDate;

import java.util.Date;
import java.util.Locale;

public class DataGenerator {
    private DataGenerator() {}

    public static class CardOrder {
        private CardOrder() {}

        public static CardOrderTestDataWithReplanningDate generateData(String locale) {
            Faker faker = new Faker(new Locale(locale));
            Date today = new Date();
            return new CardOrderTestDataWithReplanningDate(
                    faker.address().city(),
                    faker.date().between(new Date(today.getTime() + (1000 * 60 * 60 * 24 * 3)), new Date(today.getTime() + (1000 * 60 * 60 * 24 * 10))),
                    faker.date().between(new Date(today.getTime() + (1000 * 60 * 60 * 24 * 11)), new Date(today.getTime() + (1000 * 60 * 60 * 24 * 20))),
                    faker.name().lastName() + " " + faker.name().firstName(),
                    faker.phoneNumber().phoneNumber()
            );
        }
    }

}
