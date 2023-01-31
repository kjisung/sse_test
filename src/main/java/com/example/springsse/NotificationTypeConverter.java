package com.example.springsse;

import javax.persistence.AttributeConverter;

public class NotificationTypeConverter implements AttributeConverter<NotificationType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(NotificationType notificationType) {
        return notificationType.getCode();
    }

    @Override
    public NotificationType convertToEntityAttribute(Integer dbData) {
        return NotificationType.ofCode(dbData);
    }
}
