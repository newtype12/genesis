package com.example.genesis.data.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_order")
@ToString
public class Order implements Serializable {

    static final long serialVersionUid= 1l;
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    private String customerName;

    private String title;

    private String content;

    private Integer status;

    private String process;

    private Integer lastUpdateUser;

}
