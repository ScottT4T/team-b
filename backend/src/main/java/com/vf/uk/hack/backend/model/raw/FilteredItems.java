package com.vf.uk.hack.backend.model.raw;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.TreeSet;

@Data
@NoArgsConstructor
public class FilteredItems {
    private Set<String> model = new TreeSet<>();
    private Set<String> brand = new TreeSet<>();
    private Set<String> screenSize = new TreeSet<>();
    private Set<String> memoryInternal = new TreeSet<>();
    private Set<String> colour = new TreeSet<>();


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
