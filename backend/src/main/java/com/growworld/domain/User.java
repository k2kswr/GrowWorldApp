package com.growworld.domain;
import jakarta.persistence.*; import java.time.Instant; import java.util.UUID;
@Entity @Table(name="users") public class User {
 @Id private UUID id; @Column(nullable=false,unique=true) private String email; @Column(name="password_hash",nullable=false) private String passwordHash; @Column(name="display_name",nullable=false) private String displayName; @Column(name="created_at") private Instant createdAt; @Column(name="updated_at") private Instant updatedAt;
 protected User() {} public User(String email,String passwordHash,String displayName){this.id=UUID.randomUUID();this.email=email;this.passwordHash=passwordHash;this.displayName=displayName;this.createdAt=Instant.now();this.updatedAt=createdAt;}
 public UUID getId(){return id;} public String getEmail(){return email;} public String getPasswordHash(){return passwordHash;} public String getDisplayName(){return displayName;}
}
