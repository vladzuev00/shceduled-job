INSERT INTO jobs(id, status, type, run_mode, init_time, run_interval_value, run_interval_scale, params, user_id)
VALUES(256, 'ACTIVE', 'REPORT_SENDER', 'PERIODIC', '2023-04-12 13:19:34', 1, 'DAY',
    '{"first-property": "first-value", "second-property": "second-value"}', 255);

INSERT INTO job_histories(id, time, status, job_id)
VALUES(257, '2023-04-12 13:19:35', 'SUCCESS', 256);