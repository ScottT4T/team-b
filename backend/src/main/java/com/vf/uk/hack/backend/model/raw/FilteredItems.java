package com.vf.uk.hack.backend.model.raw;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

@Data
@NoArgsConstructor
public class FilteredItems {
    private Set<String> model = new TreeSet<>();
    private Set<String> brand = new TreeSet<>();
    private Set<String> screenSize = new TreeSet<>(new ScreenSizeComparator());
    private Set<String> memoryInternal = new TreeSet<>(new MemoryComparator());
    private Set<String> colour = new TreeSet<>();


  public class ScreenSizeComparator implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
      double d1 = parseDouble(o1);
      double d2 = parseDouble(o2);
      return Double.compare(d1, d2);
    }
  }

  public class MemoryComparator implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
      if(o1.endsWith("TB")) {
        o1 = o1.substring(0, o1.length()-2) + "000";
      }
      if(o2.endsWith("TB")) {
        o2 = o2.substring(0, o2.length()-2) + "000";
      }
      if(o1.endsWith("GB")) {
        o1 = o1.substring(0, o1.length()-2);
      }
      if(o2.endsWith("GB")) {
        o2 = o2.substring(0, o2.length()-2);
      }
      return parseInt(o1) - parseInt(o2);
    }
  }


/**
 *     {
 *       "model": [
 *       "Pixel Pro 7",
 *         "Pixel 7",
 *         "iPhone 12"
 *     ],
 *       "brand": [
 *       "Google",
 *         "Apple"
 *     ],
 *       "screenSize": [
 *       "6.1",
 *         "6.2"
 *     ],
 *       "memoryInternal": [
 *       "128GB",
 *         "256GB"
 *     ],
 *       "colour": [
 *       "Black",
 *         "White"
 *     ]
 *     }
 */
}
