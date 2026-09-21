package com.growworld.domain;
import jakarta.persistence.*; import java.time.*; import java.util.UUID;
@Entity @Table(name="activity_records") public class ActivityRecord {
 @Id private UUID id; @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="user_id",nullable=false) private User user; @Enumerated(EnumType.STRING) private ActivityCategory category; @Column(name="duration_minutes") private int durationMinutes; @Column(name="exp_awarded") private int expAwarded; @Column(name="performed_on") private LocalDate performedOn; @Column(name="created_at") private Instant createdAt;
 protected ActivityRecord(){} public ActivityRecord(User u,ActivityCategory c,int minutes,LocalDate date){id=UUID.randomUUID();user=u;category=c;durationMinutes=minutes;expAwarded=minutes;performedOn=date;createdAt=Instant.now();}
 public UUID getId(){return id;} public ActivityCategory getCategory(){return category;} public int getDurationMinutes(){return durationMinutes;} public int getExpAwarded(){return expAwarded;} public LocalDate getPerformedOn(){return performedOn;} public Instant getCreatedAt(){return createdAt;}
}
