package com.example.springsse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificationResponseDto {
    private boolean isRead;

    public static NotificationResponseDto creat(Notification notification) {
        return NotificationResponseDto.builder()
                .isRead(notification.getIsRead()).build();
    }
}
