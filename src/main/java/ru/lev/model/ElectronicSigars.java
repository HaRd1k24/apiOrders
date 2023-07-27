package ru.lev.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "electrosigars",schema = "public")
public class ElectronicSigars{
    @Id
    private String name;
    private int count;
}
