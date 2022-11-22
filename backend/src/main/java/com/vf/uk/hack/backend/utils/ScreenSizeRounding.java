package com.vf.uk.hack.backend.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static java.lang.Double.parseDouble;

public class ScreenSizeRounding {

  public static String roundScreenSize(final String size) {
    double updatedSize = round(parseDouble(size), 1);
    return Double.toString(updatedSize);
  }

  public static double round(double value, int places) {
    if (places < 0) throw new IllegalArgumentException();

    BigDecimal bd = BigDecimal.valueOf(value);
    bd = bd.setScale(places, RoundingMode.HALF_UP);
    return bd.doubleValue();
  }
}
