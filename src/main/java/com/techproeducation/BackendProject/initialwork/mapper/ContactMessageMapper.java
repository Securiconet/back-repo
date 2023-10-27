package com.techproeducation.BackendProject.initialwork.mapper;

import com.techproeducation.BackendProject.initialwork.dto.ContactMessageDTO;
import com.techproeducation.BackendProject.initialwork.entity.ContactMessage;

public class ContactMessageMapper {
    public static void toEntityFromDTO(ContactMessage contactMessage, ContactMessageDTO dto) {
        if (dto.getName() != null) {
            contactMessage.setName(dto.getName());
        }
        if (dto.getEmail() != null) {
            contactMessage.setEmail(dto.getEmail());
        }
        if (dto.getSubject() != null) {
            contactMessage.setSubject(dto.getSubject());
        }
        if (dto.getMessage() != null) {
            contactMessage.setMessage(dto.getMessage());
        }
    }
    public static ContactMessageDTO toDTO(ContactMessage contactMessage) {
        ContactMessageDTO contactMessageDTO = new ContactMessageDTO();
        if (contactMessage.getName() != null) {
            contactMessageDTO.setName(contactMessage.getName());
        }
        if (contactMessage.getEmail() != null) {
            contactMessageDTO.setEmail(contactMessage.getEmail());
        }
        if (contactMessage.getSubject() != null) {
            contactMessageDTO.setSubject(contactMessage.getSubject());
        }
        if (contactMessage.getMessage() != null) {
            contactMessageDTO.setMessage(contactMessage.getMessage());
        }
        return contactMessageDTO;
    }
    public static ContactMessage toEntity(ContactMessageDTO contactMessageDTO) {
        ContactMessage contactMessage = new ContactMessage();

        if (contactMessageDTO.getName() != null) {
            contactMessage.setName(contactMessageDTO.getName());
        }
        if (contactMessageDTO.getEmail() != null) {
            contactMessage.setEmail(contactMessageDTO.getEmail());
        }
        if (contactMessageDTO.getSubject() != null) {
            contactMessage.setSubject(contactMessageDTO.getSubject());
        }
        if (contactMessageDTO.getMessage() != null) {
            contactMessage.setMessage(contactMessageDTO.getMessage());
        }

        return contactMessage;
    }
}
