package ru.lev.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter

/**
 * nameSigares - ключ в боди
 * count - ключ в боди
 */

public class Buyer {
    private String nameSigares;
    private int count;
}
