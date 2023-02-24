package com.theteapottroopers.farmwatch.mapper;

/**
 * @Author: M.S. Pilat <pilat_m@msn.com>
 * <p>
 * dit programma doet iets
 */

public abstract class Mapper {

    public String emptyToNull(String value) {
        return (value != null && !value.isEmpty()) ? value : null;
    }
}
