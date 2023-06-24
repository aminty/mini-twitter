package twitter.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import twitter.base.domain.BaseEntity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user_table")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User extends BaseEntity<Long> {

    private String firstname;

    private String lastname;

    private String username;

    private String password;

    private String bio;

    private String email;

    @CreationTimestamp
    private Timestamp registerAt;

    @ManyToMany
    @JoinTable(
                    name = "follower_table",
                    joinColumns = @JoinColumn(name = "user_id"),
                    inverseJoinColumns = @JoinColumn(name = "follower_id")
    )
    private Set<User> follower=new HashSet<User>();

    @ManyToMany(mappedBy = "follower")
    private Set<User> following=new HashSet<User>();





}
