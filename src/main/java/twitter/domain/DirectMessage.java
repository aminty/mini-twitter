package twitter.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import twitter.base.domain.BaseEntity;
import twitter.base.service.BaseService;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "direct_table")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DirectMessage extends BaseEntity<Long> {

    @OneToOne
    private User sender;

    @OneToOne
    private User receiver;

    @Column(name = "message")
    private String message;

    @Column(name = "is_read")
    private boolean isRead;

    @CreationTimestamp
    @Column(name = "sent_at")
    private Timestamp sentAt;
}
