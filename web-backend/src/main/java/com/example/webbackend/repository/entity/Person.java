package com.example.webbackend.repository.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Person {
    public Person() {
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String username;

    @Column
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "person_followers",
            joinColumns = @JoinColumn(name = "following_id"),
            inverseJoinColumns = @JoinColumn(name = "follower_id")
    )
    private Set<Person> followers = new HashSet<>();

    @ManyToMany(mappedBy = "followers")
    private Set<Person> following = new HashSet<>();

    // Helper methods for managing followers/following relationships
    public void followPerson(Person personToFollow) {
        personToFollow.getFollowers().add(this);
        this.following.add(personToFollow);
    }

    public void unfollowPerson(Person personToUnfollow) {
        personToUnfollow.getFollowers().remove(this);
        this.following.remove(personToUnfollow);
    }

    public boolean isFollowing(Person person) {
        return this.following.contains(person);
    }

    public int getFollowersCount() {
        return this.followers.size();
    }

    public int getFollowingCount() {
        return this.following.size();
    }

}
