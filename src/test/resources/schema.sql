CREATE TYPE job_status AS ENUM('ACTIVE', 'PAUSE', 'ARCHIVE');

CREATE TYPE job_type AS ENUM('REPORT_SENDER');

CREATE TYPE job_run_mode AS ENUM('PERIODIC', 'ONCE');

CREATE TYPE job_run_interval_scale AS ENUM('DAY', 'WEEK', 'MONTH');

CREATE TABLE users(
    id SERIAL PRIMARY KEY
);

CREATE TABLE jobs(
    id SERIAL PRIMARY KEY,
    status job_status NOT NULL,
    type job_type NOT NULL,
    run_mode job_run_mode NOT NULL,
    init_time TIMESTAMP(0) NOT NULL,
    run_interval_value INTEGER NOT NULL,
    run_interval_scale job_run_interval_scale NOT NULL,
    params JSONB NOT NULL,
    user_id INTEGER NOT NULL
);

ALTER TABLE jobs
    ADD CONSTRAINT fk_jobs_to_users FOREIGN KEY (user_id) REFERENCES users(id)
        ON DELETE CASCADE;

CREATE TYPE job_history_status AS ENUM('SUCCESS', 'FAILED');

CREATE TABLE job_histories(
    id SERIAL PRIMARY KEY,
    time TIMESTAMP(0) NOT NULL,
    status job_history_status NOT NULL,
    job_id INTEGER NOT NULL
);

ALTER TABLE job_histories
    ADD CONSTRAINT fk_job_histories_to_jobs FOREIGN KEY (job_id) REFERENCES jobs(id)
        ON DELETE CASCADE;