-- Copyright (c) ZNbase, Inc.
alter table backup add column schedule_uuid uuid;
alter table backup add column expiry timestamp;
