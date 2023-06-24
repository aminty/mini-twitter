package twitter.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import twitter.base.domain.BaseEntity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "comment_table")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Comment extends BaseEntity<Long> {

    @OneToOne
    private User commentOwner;

    @Column(name = "message")
    private String message;


    @ManyToOne
    private Tweet tweetComment;


    @CreationTimestamp
    @Column(name = "wrote_at")
    private Timestamp wroteAt;


}
