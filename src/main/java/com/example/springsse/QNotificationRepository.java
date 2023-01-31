package com.example.springsse;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.springsse.QNotification.notification;


@Repository
@RequiredArgsConstructor
public class QNotificationRepository {
    public final JPAQueryFactory jpaQueryFactory;

    public List<Notification> findByUnreadNotification(Member receiver, NotificationType type ) {
        return jpaQueryFactory
                .selectFrom(notification)
                .where(notification.receiver.eq(receiver), notification.notificationType.eq(type), notification.isRead.eq(false))
                .fetch();
    }
}
