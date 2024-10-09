DROP table if exists process_time_log;

CREATE TABLE process_time_log (
  id uuid NOT NULL DEFAULT gen_random_uuid() ,
  start_time_ns bigint NOT NULL,
  end_time_ns bigint NOT NULL,
  start_time TIMESTAMPTZ NOT NULL,
  end_time TIMESTAMPTZ NOT NULL,
  process_time double precision NOT NULL,
  service_name VARCHAR(50) NOT NULL,
  host_name VARCHAR(50) NOT NULL,
  ip_address VARCHAR(50) NOT NULL
);

drop index ix_process_time;

CREATE UNIQUE INDEX ix_process_time ON process_time_log (start_time DESC);

SELECT create_hypertable('process_time_log', 'start_time', if_not_exists => TRUE, chunk_time_interval => INTERVAL '1 day');