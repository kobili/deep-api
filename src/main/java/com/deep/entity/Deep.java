package com.deep.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class Deep {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String deep;
}
