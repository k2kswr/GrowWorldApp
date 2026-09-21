package com.growworld.dto;
import java.util.*; import com.growworld.dto.ActivityDtos.Item;
public final class DashboardDtos { private DashboardDtos(){} public record Unlock(String key,String displayName,int requiredLevel){} public record Progress(int totalExp,int level,int currentLevelExp,Integer nextLevelExp,Integer expToNextLevel){} public record Dashboard(Progress progress,long activeDays,List<Item> todayActivities,List<Unlock> world){} public record CreatedActivity(Item activity,Dashboard dashboard,List<Unlock> newlyUnlocked){} }
