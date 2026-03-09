package com.example.tz.Models.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@Entity
@Table(name = "contact")
public class ContactsEntity implements Serializable {

    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Setter
    @Getter
    private String phone;

    @Setter
    @Getter
    private String email;

    @Setter
    @Getter
    @OneToOne(mappedBy = "contacts")
    @ToString.Exclude
    private HotelEntity hotel;
}
