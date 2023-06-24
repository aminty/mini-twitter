package twitter.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import twitter.base.domain.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "like_table")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Like extends BaseEntity<Long> {


    @OneToOne
    private User likeOwner;



    @ManyToOne
    private Tweet tweetLike;


}
