package com.techproeducation.BackendProject.initialwork.repository;

import com.techproeducation.BackendProject.initialwork.entity.ContactMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ContactMessageRepository extends JpaRepository<ContactMessage, Long> {
    List<ContactMessage>findAllByEmail(String email);
}
