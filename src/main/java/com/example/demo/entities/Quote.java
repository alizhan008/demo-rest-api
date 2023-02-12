package com.example.demo.entities;

import lombok.Data;
import javax.persistence.*;


@Data
@Entity
@Table(name = "quotes")
public class Quote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "vote")
    private Integer vote;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private Users users;

}
