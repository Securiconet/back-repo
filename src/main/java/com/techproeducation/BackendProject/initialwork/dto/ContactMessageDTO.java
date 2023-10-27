package com.techproeducation.BackendProject.initialwork.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@ToString

public class ContactMessageDTO {
    @NotNull
    private String name;

    @NotNull
    @Email
    private String email;

    @NotNull
    private String subject;

    @NotNull
    private String message;

    @Setter(AccessLevel.NONE)
    private LocalDateTime updateDateTime;

    public ContactMessageDTO() {
        updateDateTime = LocalDateTime.now(ZoneId.of("US/Eastern"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formattedDate = updateDateTime.format(formatter);
        updateDateTime = LocalDateTime.parse(formattedDate, formatter).withSecond(0).withNano(0);
    }

}
