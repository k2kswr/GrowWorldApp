package com.growworld.domain;
import jakarta.persistence.*;
@Entity @Table(name="level_thresholds") public class LevelThreshold { @Id private int level; @Column(name="required_total_exp") private int requiredTotalExp; public int getLevel(){return level;} public int getRequiredTotalExp(){return requiredTotalExp;} }
