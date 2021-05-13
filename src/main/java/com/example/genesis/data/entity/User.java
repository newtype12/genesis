package com.example.genesis.data.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    static final long serialVersionUid = 1l;

    @Id
    private Integer id;

    private String name;

    private String pw;

    private Integer role;

    private Integer status;

    @Column(name = "order_id")
    private Integer orderId;
}
