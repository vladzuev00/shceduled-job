CREATE TYPE scheduled_task_status AS ENUM('ACTIVE', 'PAUSE', 'ARCHIVE');

CREATE TYPE scheduled_task_type AS ENUM('REPORT_SENDER');

CREATE TYPE scheduled_task_run_mode AS ENUM('PERIODIC', 'ONCE');

CREATE TYPE scheduled_task_run_interval_scale AS ENUM('SECOND', 'MINUTE', 'HOUR', 'DAY', 'WEEK');

CREATE TABLE users(
    id SERIAL PRIMARY KEY
);

CREATE TABLE scheduled_tasks(
    id SERIAL PRIMARY KEY,
    status scheduled_task_status NOT NULL,
    type scheduled_task_type NOT NULL,
    run_mode scheduled_task_run_mode NOT NULL,
    init_time TIMESTAMP(0) NOT NULL,
    run_interval_value INTEGER NOT NULL,
    run_interval_scale scheduled_task_run_interval_scale NOT NULL,
    params JSONB NOT NULL,
    user_id INTEGER NOT NULL
);

ALTER TABLE scheduled_tasks
    ADD CONSTRAINT fk_scheduled_tasks_to_users FOREIGN KEY (user_id) REFERENCES users(id)
        ON DELETE CASCADE;

CREATE TYPE scheduled_task_history_status AS ENUM('SUCCESS', 'FAILED');

CREATE TABLE scheduled_task_histories(
    id SERIAL PRIMARY KEY,
    time TIMESTAMP(0) NOT NULL,
    status scheduled_task_history_status NOT NULL,
    scheduled_task_id INTEGER NOT NULL
);

ALTER TABLE scheduled_task_histories
    ADD CONSTRAINT fk_scheduled_task_histories_to_scheduled_tasks FOREIGN KEY (job_id) REFERENCES jobs(id)
        ON DELETE CASCADE;