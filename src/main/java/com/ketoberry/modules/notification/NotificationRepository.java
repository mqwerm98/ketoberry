package com.ketoberry.modules.notification;

import com.ketoberry.modules.account.entity.Account;
import com.ketoberry.modules.notification.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    long countByAccountAndChecked(Account account, boolean checked);
}
