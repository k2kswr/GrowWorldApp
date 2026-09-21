package com.growworld;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.growworld.domain.*;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class ActivityRecordTest {
  @Test void awardsOneExpForEveryRecordedMinute() {
    var user = new User("test@example.com", "hash", "Test");
    var record = new ActivityRecord(user, ActivityCategory.STUDY, 30, LocalDate.of(2026, 9, 20));
    assertEquals(30, record.getExpAwarded());
  }
}
