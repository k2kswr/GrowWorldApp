package com.growworld.domain;
import jakarta.persistence.*; import java.util.UUID;
@Entity @Table(name="world_unlocks") public class WorldUnlock { @Id private UUID id; @Column(name="required_level") private int requiredLevel; @Column(name="element_key") private String elementKey; @Column(name="display_name") private String displayName; @Column(name="sort_order") private int sortOrder; public int getRequiredLevel(){return requiredLevel;} public String getElementKey(){return elementKey;} public String getDisplayName(){return displayName;} public int getSortOrder(){return sortOrder;} }
