package com.vf.uk.hack.backend.utils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ColourNames {

  private static final List<String> STANDARD_COLOURS = Arrays.asList(
     "Red",
     "Green",
     "Black",
     "Blue",
     "Grey",
     "Purple",
     "Orange",
     "Gold",
     "Pink",
     "White",
     "Yellow");

  private static final Map<String, String> SPECIAL_MAP_COLOURS = new TreeMap<>();
  static {
    SPECIAL_MAP_COLOURS.putAll(Map.of(
      "Mint", "Green",
      "Peach", "Orange",
      "Violet", "Purple",
      "Burgundy", "Red",
      "Chalk", "White",
      "Charcoal", "Grey",
      "Lavender", "Purple",
      "Navy", "Blue",
      "Coral", "Pink",
      "Graphite", "Grey"));
    SPECIAL_MAP_COLOURS.putAll(Map.of(
      "Gray", "Grey",
      "Hazel", "Green",
      "Lemongrass", "Yellow",
      "Midnight", "Black",
      "Night", "Black",
      "Obsidian", "Black",
      "Olive", "Green",
      "Sage", "Green",
      "Silver", "Grey",
      "Snow", "White"));
      SPECIAL_MAP_COLOURS.putAll(Map.of(
      "SortaSeafoam", "Blue",
      "Starlight", "White"));
  }

  public static String findColorNameByColor(final String name) {
    for(String colourName : STANDARD_COLOURS) {
      if(name.contains(colourName)) {
        return colourName;
      }
    }

    for(Map.Entry<String,String> entry : SPECIAL_MAP_COLOURS.entrySet()) {
      if(name.contains(entry.getKey())) {
        return entry.getValue();
      }
    }
    return name;
  }
}
