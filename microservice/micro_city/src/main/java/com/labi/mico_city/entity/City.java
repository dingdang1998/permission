package com.labi.mico_city.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author admin
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class City implements Serializable {
    private Integer id;
    private String name;
    /**
     * 城市面积
     */
    private double area;
}
