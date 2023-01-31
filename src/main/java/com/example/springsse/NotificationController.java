package com.example.springsse;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    /**
     * @title 로그인 한 유저 sse 연결
     */
    @GetMapping(value = "/subscribe/{id}", produces = "text/event-stream")
    public SseEmitter subscribe(@PathVariable Long id,
                                @RequestHeader(value = "Last-Event-ID", required = false, defaultValue = "") String lastEventId) {
        return notificationService.subscribe(id, lastEventId);
    }

//    @PostMapping("/noty")
//    public void creatNoty(@RequestBody NotificationDto notificationDto) {
//        System.out.println("notificationDto.g = " + notificationDto.g);
//        notificationService.creatNoty(notificationDto);
//    }

    @PostMapping("/notification/read")
    public NotificationResponseDto readNotification(@RequestBody NotificationDto notificationDto) {
        return notificationService.readNotification(notificationDto);
    }

}