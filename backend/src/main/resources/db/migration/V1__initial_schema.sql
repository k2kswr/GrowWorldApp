CREATE TABLE users (
  id UUID PRIMARY KEY,
  email VARCHAR(254) NOT NULL UNIQUE,
  password_hash VARCHAR(100) NOT NULL,
  display_name VARCHAR(40) NOT NULL,
  created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
  updated_at TIMESTAMPTZ NOT NULL DEFAULT now()
);
CREATE TABLE activity_records (
  id UUID PRIMARY KEY,
  user_id UUID NOT NULL REFERENCES users(id) ON DELETE CASCADE,
  category VARCHAR(30) NOT NULL,
  duration_minutes INTEGER NOT NULL CHECK (duration_minutes BETWEEN 1 AND 600),
  exp_awarded INTEGER NOT NULL CHECK (exp_awarded > 0),
  performed_on DATE NOT NULL,
  created_at TIMESTAMPTZ NOT NULL DEFAULT now()
);
CREATE INDEX idx_activity_user_date ON activity_records(user_id, performed_on);
CREATE INDEX idx_activity_user_created ON activity_records(user_id, created_at DESC);
CREATE TABLE level_thresholds (level INTEGER PRIMARY KEY, required_total_exp INTEGER NOT NULL UNIQUE);
CREATE TABLE world_unlocks (id UUID PRIMARY KEY, required_level INTEGER NOT NULL REFERENCES level_thresholds(level), element_key VARCHAR(40) NOT NULL UNIQUE, display_name VARCHAR(40) NOT NULL, sort_order INTEGER NOT NULL);
INSERT INTO level_thresholds(level, required_total_exp) VALUES
 (1,0),(2,30),(3,80),(4,150),(5,240),(6,350),(7,480),(8,630),(9,800),(10,990),(11,1200),(12,1430),(13,1680),(14,1950),(15,2240),(16,2550),(17,2880),(18,3230),(19,3600),(20,3990),(21,4400),(22,4830),(23,5280),(24,5750),(25,6240),(26,6750),(27,7280),(28,7830),(29,8400),(30,9000);
INSERT INTO world_unlocks(id,required_level,element_key,display_name,sort_order) VALUES
 ('00000000-0000-0000-0000-000000000001',1,'meadow','草原',1),
 ('00000000-0000-0000-0000-000000000002',2,'grass','草',2),
 ('00000000-0000-0000-0000-000000000003',3,'trees','木',3),
 ('00000000-0000-0000-0000-000000000004',5,'animals','動物',4),
 ('00000000-0000-0000-0000-000000000005',8,'house','家',5),
 ('00000000-0000-0000-0000-000000000006',12,'village','村',6),
 ('00000000-0000-0000-0000-000000000007',20,'town','街',7),
 ('00000000-0000-0000-0000-000000000008',30,'city','都市',8);
