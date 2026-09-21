package com.growworld.service;

import com.growworld.domain.*;
import com.growworld.dto.ActivityDtos.*;
import com.growworld.dto.DashboardDtos.*;
import com.growworld.repository.*;
import java.time.*;
import java.util.*;
import java.util.stream.Collectors;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@Service
public class ProgressService {
  private static final ZoneId TOKYO = ZoneId.of("Asia/Tokyo");
  private final UserRepository users; private final ActivityRecordRepository activities;
  private final LevelThresholdRepository levels; private final WorldUnlockRepository unlocks;
  public ProgressService(UserRepository u, ActivityRecordRepository a, LevelThresholdRepository l, WorldUnlockRepository w) { users=u; activities=a; levels=l; unlocks=w; }
  public Dashboard dashboard(UUID id) { return build(id, activities.totalExp(id)); }
  @Transactional public CreatedActivity create(UUID id, CreateRequest request) {
    var user=users.findById(id).orElseThrow(()->new ResponseStatusException(UNAUTHORIZED));
    int before=activities.totalExp(id);
    var record=activities.save(new ActivityRecord(user,request.category(),request.durationMinutes(),LocalDate.now(TOKYO)));
    int after=before+record.getExpAwarded();
    var prior=world(before).stream().map(Unlock::key).collect(Collectors.toSet());
    var newlyUnlocked=world(after).stream().filter(x->!prior.contains(x.key())).toList();
    return new CreatedActivity(item(record),build(id,after),newlyUnlocked);
  }
  public PageResponse history(UUID id,int page,int size) { var result=activities.findByUserIdOrderByCreatedAtDesc(id,PageRequest.of(page,size)); return new PageResponse(result.getContent().stream().map(this::item).toList(),result.getNumber(),result.getSize(),result.getTotalElements(),result.getTotalPages()); }
  private Dashboard build(UUID id,int exp) { var today=activities.findByUserIdAndPerformedOnOrderByCreatedAtDesc(id,LocalDate.now(TOKYO)).stream().map(this::item).toList(); return new Dashboard(progress(exp),activities.countDistinctByUserId(id),today,world(exp)); }
  private Progress progress(int exp) { var all=levels.findAll(Sort.by("level")); var current=all.stream().filter(x->x.getRequiredTotalExp()<=exp).reduce((a,b)->b).orElseThrow(); var next=all.stream().filter(x->x.getLevel()==current.getLevel()+1).findFirst(); return new Progress(exp,current.getLevel(),current.getRequiredTotalExp(),next.map(LevelThreshold::getRequiredTotalExp).orElse(null),next.map(x->x.getRequiredTotalExp()-exp).orElse(null)); }
  private List<Unlock> world(int exp) { int level=progress(exp).level(); return unlocks.findByRequiredLevelLessThanEqualOrderBySortOrder(level).stream().map(x->new Unlock(x.getElementKey(),x.getDisplayName(),x.getRequiredLevel())).toList(); }
  private Item item(ActivityRecord a) { return new Item(a.getId(),a.getCategory(),a.getDurationMinutes(),a.getExpAwarded(),a.getPerformedOn(),a.getCreatedAt()); }
}
