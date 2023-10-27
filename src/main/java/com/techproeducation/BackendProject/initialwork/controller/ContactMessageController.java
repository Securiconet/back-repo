package com.techproeducation.BackendProject.initialwork.controller;

import com.techproeducation.BackendProject.initialwork.dto.ContactMessageDTO;
import com.techproeducation.BackendProject.initialwork.entity.ContactMessage;
import com.techproeducation.BackendProject.initialwork.mapper.ContactMessageMapper;
import com.techproeducation.BackendProject.initialwork.repository.ContactMessageRepository;
import com.techproeducation.BackendProject.initialwork.service.ContactMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/messages") //http://localhost:8080/messages
public class ContactMessageController {

    @Autowired
    private ContactMessageService contactMessageService;
    @GetMapping //http://localhost:8080/messages + GET
    public ResponseEntity<List<ContactMessage>> getAllContactMessages(){
        List<ContactMessage> allContactMessages = contactMessageService.findAllContactMessages();
       return ResponseEntity.ok(allContactMessages);
    }

    @GetMapping("/{id}")//http://localhost:8080/messages/2
    public ResponseEntity<ContactMessage> findMessageById(@PathVariable Long id) {
        ContactMessage contactMessage = contactMessageService.getMessageById(id);
        return new ResponseEntity<>(contactMessage, HttpStatus.OK);
    }
    @GetMapping("/email") //http://localhost:8080/messages/email?
    public ResponseEntity<List<ContactMessage>> getMessagesByEmail(@RequestParam String email) {
        List<ContactMessage> allMessagesByEmail = contactMessageService.findMessagesByEmail(email);
        return new ResponseEntity<>(allMessagesByEmail, HttpStatus.OK);
    }
    @GetMapping("/search")
    public ResponseEntity<List<ContactMessage>> findAllWordContainerMessages(@RequestParam String word) {
        List<ContactMessage> wordContainerMessages = contactMessageService.findAllWordContainerMessages(word);
        return ResponseEntity.ok(wordContainerMessages);
    }

    @PostMapping //http://localhost:8080/messages + POST
    public ResponseEntity<Map<String, String>> sendMessage(@Valid @RequestBody ContactMessageDTO contactMessageDTO){

        ContactMessage contactMessage = ContactMessageMapper.toEntity(contactMessageDTO);
        contactMessageService.saveMessage(contactMessage);
        Map<String, String> map = new HashMap<>();
        map.put("message",  "message was sent successfully");
        map.put("status", "true");
        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMessageByID(@PathVariable Long id) {
        contactMessageService.deleteMessageByID(id);
        return new ResponseEntity<>("Message is deleted successfully!", HttpStatus.OK);
    }
    @DeleteMapping
    public ResponseEntity<String> deleteMessageByPath(@RequestParam Long id) {
        contactMessageService.deleteMessageByPath(id);
        return new ResponseEntity<>("Message is deleted successfully!", HttpStatus.OK);
    }
    @GetMapping("/pagination")
    public ResponseEntity<List<ContactMessage>> getMessagesByPagination(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("sort") String sort,
            @RequestParam("direction") Sort.Direction direction
    ) {
        Pageable pageable = PageRequest.of(page,size, Sort.by(direction,sort));
        List<ContactMessage> messagesByPagination = contactMessageService.getMessagesByPagination(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(messagesByPagination);
    }
    @GetMapping("/findByDateRange")
    public ResponseEntity<List<ContactMessage>> findMessagesByDateRange(//2023-10-15  2023-10-25
                                                                        @RequestParam String startDate,
                                                                        @RequestParam String endDate
    ) {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        List<ContactMessage> messages = contactMessageService.findMessagesByDateRange(start, end);
        return ResponseEntity.ok(messages);
    }
    @GetMapping("/findByTimeRange")
    public ResponseEntity<List<ContactMessage>> findMessagesByTimeRange(
            @RequestParam String startTime,
            @RequestParam String endTime
    ) {
        LocalTime start = LocalTime.parse(startTime);
        LocalTime end = LocalTime.parse(endTime);
        List<ContactMessage> contactMessageList = contactMessageService.findMessagesByTimeRange(start, end);
        return ResponseEntity.status(HttpStatus.OK).body(contactMessageList);

    }

    @PatchMapping("/{id}")
    public ResponseEntity<ContactMessage> updateContactMessage8(
            @PathVariable Long id,
            @Valid @RequestBody ContactMessageDTO contactMessageDTO,
            BindingResult bindingResult
    ) {
        ContactMessage updatedMessage = contactMessageService.updateMessageByID(id, contactMessageDTO, bindingResult);
        return ResponseEntity.ok(updatedMessage);
    }

}
