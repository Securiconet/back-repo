package com.techproeducation.BackendProject.initialwork.entity;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;


@Builder
@Getter
@Setter
@AllArgsConstructor
@ToString
@Entity

public class ContactMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    @Setter(AccessLevel.NONE)
    private Long id;

    @NotBlank(message = "name cannot be empty or null")
    @NotEmpty(message = "name cannot be empty")
    @NotNull(message = "name cannot be null")
    @Size(min = 2, max = 25, message = "Name should be between {min} and {max}.")
    @Column(nullable = false, length = 25)
    private String name;

    @NotBlank(message = "email cannot be empty or null")
    @NotEmpty(message = "email cannot be empty")
    @NotNull(message = "email cannot be null")
    @Email(message = "provide a valid email address")
    private String email;

    @NotBlank(message = "subject cannot be empty or null")
    @NotEmpty(message = "subject cannot be empty")
    @NotNull(message = "subject cannot be null")
    private String subject;

    @NotNull(message = "Message Field can not be null!")
    @NotBlank(message = "Message Field can not be white space")
    @NotEmpty(message = "Message Field can not leave empty")
    private String message;

    //private LocalDateTime localDateTime = LocalDateTime.now();

    @Setter(AccessLevel.NONE)
    private LocalDateTime creationDateTime;

    public ContactMessage() {
        creationDateTime = LocalDateTime.now(ZoneId.of("US/Eastern"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formattedDate = creationDateTime.format(formatter);
        creationDateTime = LocalDateTime.parse(formattedDate, formatter).withSecond(0).withNano(0);
    }
    }

