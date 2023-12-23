package ru.netology.util.data;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Data
@RequiredArgsConstructor
public class CardOrderTestDataWithReplanningDate {
    private final String city;
    private final Date date;
    private final Date replanningDate;
    private final String name;
    private final String phoneNumber;
}
