package com.techproeducation.BackendProject.initialwork.service;

import com.techproeducation.BackendProject.initialwork.ContactMessageApplication;
import com.techproeducation.BackendProject.initialwork.dto.ContactMessageDTO;
import com.techproeducation.BackendProject.initialwork.entity.ContactMessage;
import com.techproeducation.BackendProject.initialwork.exceptions.ResourceIsNotFoundException;
import com.techproeducation.BackendProject.initialwork.mapper.ContactMessageMapper;
import com.techproeducation.BackendProject.initialwork.repository.ContactMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import javax.xml.bind.ValidationException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ContactMessageService {

    @Autowired
    private ContactMessageRepository contactMessageRepository;

    public List<ContactMessage>findAllContactMessages(){
        return contactMessageRepository.findAll();
    }

    public void deleteMessageByID(Long id) {
        ContactMessage contactMessage = getMessageById(id);
        contactMessageRepository.deleteById(id);
    }
    public void deleteMessageByPath(Long id) {
        ContactMessage contactMessage = getMessageById(id);
        contactMessageRepository.deleteById(id);
    }
    public List<ContactMessage> findAllWordContainerMessages(String word) {
        List<ContactMessage> allMessages = contactMessageRepository.findAll();
        List<ContactMessage> wordContainerMessages = new ArrayList<>();
        for (ContactMessage eachMessage : allMessages) {
            if (eachMessage.getMessage().contains(word) || eachMessage.getSubject().contains(word)) {
                wordContainerMessages.add(eachMessage);
            }
        }
        if (wordContainerMessages.isEmpty()) {
            throw new ResourceIsNotFoundException("No message is found with the given: " + word);
        }
        return wordContainerMessages;
    }

    public ContactMessage getMessageById(Long id) {
        return contactMessageRepository.findById(id)
                .orElseThrow(() -> new ResourceIsNotFoundException("No message is found with the given: " + id));
    }
    public List<ContactMessage> findMessagesByEmail(String email) {
        return contactMessageRepository.findAllByEmail(email);
    }

    public List<ContactMessage> getMessagesByPagination(Pageable pageable) {
        //return contactMessageRepository.findAll(pageable);
        return contactMessageRepository.findAll(pageable).getContent();
    }
    public List<ContactMessage> findMessagesByDateRange(LocalDate start, LocalDate end) {
        List<ContactMessage> allMessages = findAllContactMessages();
        List<ContactMessage> betweenDateRange = new ArrayList<>();
        for (ContactMessage each : allMessages) {
            LocalDate eachCreationDate = LocalDate.from(each.getCreationDateTime());
            if (eachCreationDate.isAfter(ChronoLocalDate.from(start)) && eachCreationDate.isBefore(ChronoLocalDate.from(end))) {
                betweenDateRange.add(each);
            }
        }
        if (betweenDateRange.isEmpty()) {
            throw new ResourceIsNotFoundException("No messages is found between " + start + " and " + end);
        }
        return betweenDateRange;
    }
    public List<ContactMessage> findMessagesByTimeRange(LocalTime start, LocalTime end) {
        List<ContactMessage> allMessages = findAllContactMessages();
        List<ContactMessage> betweenTime = new ArrayList<>();
        for (ContactMessage eachMessage : allMessages) {
            LocalTime eachCreationTime = LocalTime.from(eachMessage.getCreationDateTime());
            if (eachCreationTime.isAfter(start) && eachCreationTime.isBefore(end)) {
                betweenTime.add(eachMessage);
            }
        }
        if (betweenTime.isEmpty()) {
            throw new ResourceIsNotFoundException("No messages is found between " + start + " and " + end);
        }
        return betweenTime;
    }
    public ContactMessage updateMessageByID(Long id, ContactMessageDTO contactMessageDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            try {
                throw new ValidationException("Validation error");
            } catch (ValidationException e) {
                e.printStackTrace();
            }
        }
        ContactMessage existingMessage = getMessageById(id);
        ContactMessageMapper.toEntityFromDTO(existingMessage, contactMessageDTO);
        return contactMessageRepository.save(existingMessage);
    }


    public void saveMessage(ContactMessage contactMessage) {
        contactMessageRepository.save(contactMessage);
    }
}
