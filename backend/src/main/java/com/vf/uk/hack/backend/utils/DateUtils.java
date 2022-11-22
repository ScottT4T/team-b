package com.vf.uk.hack.backend.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static java.lang.Long.parseLong;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DateUtils {
  private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

  public static LocalDate dateFromNow(int plusDays) {
    return LocalDate.now().plusDays(plusDays);
  }

  public static String formatDate(final LocalDate localDate) {
    if(localDate != null) {
      return localDate.format(FORMATTER);
    }
    return null;
  }

  public static String formatDate(final String date) {
    final LocalDate localDate = parseDate(date);
    if(localDate != null) {
      return localDate.format(FORMATTER);
    }
    return null;
  }

  public static LocalDate parseDate(final String date) {
    if (date != null) {
      boolean addDays = date.startsWith("+");
      boolean minusDays = date.startsWith("-");
      if (addDays) {
        return LocalDate.now().plusDays(parseLong(date.substring(1)));
      }
      if (minusDays) {
        return LocalDate.now().minusDays(parseLong(date.substring(1)));
      }
      return LocalDate.parse(date);
    }
    return null;
  }
}
