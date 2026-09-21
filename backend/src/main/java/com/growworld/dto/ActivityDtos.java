package com.growworld.dto;
import com.growworld.domain.ActivityCategory; import jakarta.validation.constraints.*; import java.time.*; import java.util.*;
public final class ActivityDtos { private ActivityDtos(){} public record CreateRequest(@NotNull ActivityCategory category,@Min(1) @Max(600) int durationMinutes){} public record Item(UUID id,ActivityCategory category,int durationMinutes,int expAwarded,LocalDate performedOn,Instant createdAt){} public record PageResponse(List<Item> content,int page,int size,long totalElements,int totalPages){} }
