package twitter.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import twitter.base.domain.BaseEntity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tweet_table")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Tweet extends BaseEntity<Long> {

    @ManyToOne()
    private User owner;

    @OneToMany(mappedBy = "tweetComment")
    private List<Comment> comments=new ArrayList<>();

    @OneToMany(mappedBy = "tweetLike")
    private List<Like> likes=new ArrayList<>();


    @Column(name = "message")
    private String message;

    @CreationTimestamp
    @Column(name = "created_at")
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Timestamp updatedAt;

}
