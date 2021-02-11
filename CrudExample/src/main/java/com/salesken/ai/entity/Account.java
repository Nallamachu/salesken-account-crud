package com.salesken.ai.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "ACCOUNT_NO",unique = true, nullable = false)
    private String accNo;
    private String branch;
    private String ifsc;
    private boolean active;
}
