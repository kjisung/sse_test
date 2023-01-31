package com.example.springsse;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(nullable = false)
    private Boolean isRead;

    @Convert(converter = NotificationTypeConverter.class)
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NotificationType notificationType;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")
    private Member receiver;

    @Builder
    public Notification(Member receiver, NotificationType notificationType, Boolean isRead) {
        this.receiver = receiver;
        this.notificationType = notificationType;
        this.isRead = isRead;
    }

    public void updateIsRead(boolean isRead) {
        this.isRead = isRead;
    }

}
