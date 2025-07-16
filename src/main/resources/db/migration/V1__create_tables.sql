CREATE TABLE blacklisted_refresh_tokens
(
    id          BIGINT AUTO_INCREMENT NOT NULL,
    created_at  datetime              NOT NULL,
    updated_at  datetime              NULL,
    jti         VARCHAR(255)          NOT NULL,
    member_id   BIGINT                NOT NULL,
    expiry_date datetime              NOT NULL,
    CONSTRAINT pk_blacklisted_refresh_tokens PRIMARY KEY (id)
);

CREATE TABLE favorite_spots
(
    id           BIGINT AUTO_INCREMENT NOT NULL,
    created_at   datetime              NOT NULL,
    updated_at   datetime              NULL,
    member_id    BIGINT                NOT NULL,
    spot_id      BIGINT                NOT NULL,
    notification BIT(1)                NOT NULL,
    CONSTRAINT pk_favorite_spots PRIMARY KEY (id)
);

CREATE TABLE fishing_forecast
(
    id                BIGINT AUTO_INCREMENT NOT NULL,
    created_at        datetime              NOT NULL,
    updated_at        datetime              NULL,
    spot_id           BIGINT                NOT NULL,
    target_id         BIGINT                NULL,
    forecast_date     date                  NOT NULL,
    time_period       VARCHAR(10)           NULL,
    tide              VARCHAR(255)          NULL,
    total_index       VARCHAR(255)          NOT NULL,
    wave_height_min   FLOAT                 NULL,
    wave_height_max   FLOAT                 NULL,
    sea_temp_min      FLOAT                 NULL,
    sea_temp_max      FLOAT                 NULL,
    air_temp_min      FLOAT                 NULL,
    air_temp_max      FLOAT                 NULL,
    current_speed_min FLOAT                 NULL,
    current_speed_max FLOAT                 NULL,
    wind_speed_min    FLOAT                 NULL,
    wind_speed_max    FLOAT                 NULL,
    uv_index          FLOAT                 NULL,
    CONSTRAINT pk_fishing_forecast PRIMARY KEY (id)
);

CREATE TABLE fishing_targets
(
    id   BIGINT AUTO_INCREMENT NOT NULL,
    name VARCHAR(50)           NULL,
    CONSTRAINT pk_fishing_targets PRIMARY KEY (id)
);

CREATE TABLE jellyfish_region_density
(
    id           BIGINT AUTO_INCREMENT NOT NULL,
    created_at   datetime              NOT NULL,
    updated_at   datetime              NULL,
    region_name  VARCHAR(100)          NOT NULL,
    species      BIGINT                NOT NULL,
    species_id   BIGINT                NOT NULL,
    report_date  date                  NOT NULL,
    density_type VARCHAR(10)           NOT NULL,
    CONSTRAINT pk_jellyfish_region_density PRIMARY KEY (id)
);

CREATE TABLE jellyfish_species
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    created_at datetime              NOT NULL,
    updated_at datetime              NULL,
    name       VARCHAR(20)           NOT NULL,
    toxicity   VARCHAR(255)          NOT NULL,
    CONSTRAINT pk_jellyfish_species PRIMARY KEY (id)
);

CREATE TABLE meeting_participants
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    created_at datetime              NOT NULL,
    updated_at datetime              NULL,
    meeting_id BIGINT                NOT NULL,
    user_id    BIGINT                NOT NULL,
    `role`     SMALLINT              NOT NULL,
    CONSTRAINT pk_meeting_participants PRIMARY KEY (id)
);

CREATE TABLE meetings
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    created_at    datetime              NOT NULL,
    updated_at    datetime              NULL,
    title         VARCHAR(20)           NOT NULL,
    category      SMALLINT              NOT NULL,
    capacity      INT                   NOT NULL,
    host_id       BIGINT                NOT NULL,
    meeting_time  datetime              NOT NULL,
    status        SMALLINT              NOT NULL,
    spot_id       BIGINT                NOT NULL,
    `description` TEXT                  NULL,
    CONSTRAINT pk_meetings PRIMARY KEY (id)
);

CREATE TABLE members
(
    id          BIGINT AUTO_INCREMENT NOT NULL,
    created_at  datetime              NOT NULL,
    updated_at  datetime              NULL,
    nickname    VARCHAR(20)           NOT NULL,
    email       VARCHAR(50)           NOT NULL,
    provider    VARCHAR(255)          NULL,
    provider_id VARCHAR(255)          NULL,
    status      SMALLINT              NOT NULL,
    latitude    DECIMAL(9, 6)         NULL,
    longitude   DECIMAL(9, 6)         NULL,
    CONSTRAINT pk_members PRIMARY KEY (id)
);

CREATE TABLE mudflat_forecast
(
    id             BIGINT AUTO_INCREMENT NOT NULL,
    created_at     datetime              NOT NULL,
    updated_at     datetime              NULL,
    spot_id        BIGINT                NOT NULL,
    forecast_date  date                  NOT NULL,
    start_time     time                  NULL,
    end_time       time                  NULL,
    uv_index       FLOAT                 NULL,
    air_temp_min   FLOAT                 NULL,
    air_temp_max   FLOAT                 NULL,
    wind_speed_min FLOAT                 NULL,
    wind_speed_max FLOAT                 NULL,
    weather        VARCHAR(255)          NULL,
    total_index    VARCHAR(255)          NULL,
    CONSTRAINT pk_mudflat_forecast PRIMARY KEY (id)
);

CREATE TABLE observatories
(
    id         VARCHAR(7)    NOT NULL,
    created_at datetime      NOT NULL,
    updated_at datetime      NULL,
    name       VARCHAR(255)  NOT NULL,
    latitude   DECIMAL(9, 6) NOT NULL,
    longitude  DECIMAL(9, 6) NOT NULL,
    hl_code    SMALLINT      NOT NULL,
    time       time          NOT NULL,
    CONSTRAINT pk_observatories PRIMARY KEY (id)
);

CREATE TABLE outdoor_spots
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    created_at datetime              NOT NULL,
    updated_at datetime              NULL,
    name       VARCHAR(255)          NOT NULL,
    category   VARCHAR(255)          NULL,
    type       VARCHAR(255)          NULL,
    location   VARCHAR(100)          NULL,
    latitude   DECIMAL(9, 6)         NULL,
    longitude  DECIMAL(9, 6)         NULL,
    geo_point  POINT SRID 4326       NOT NULL,
    CONSTRAINT pk_outdoor_spots PRIMARY KEY (id)
);

CREATE TABLE refresh_tokens
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    created_at    datetime              NOT NULL,
    updated_at    datetime              NULL,
    refresh_token VARCHAR(512)          NOT NULL,
    user_id       BIGINT                NOT NULL,
    expired       BIT(1)                NOT NULL,
    CONSTRAINT pk_refresh_tokens PRIMARY KEY (id)
);

CREATE TABLE scuba_forecast
(
    id                BIGINT AUTO_INCREMENT NOT NULL,
    created_at        datetime              NOT NULL,
    updated_at        datetime              NULL,
    spot_id           BIGINT                NOT NULL,
    forecast_date     date                  NOT NULL,
    time_period       VARCHAR(10)           NOT NULL,
    sunrise           time                  NULL,
    sunset            time                  NULL,
    tide              VARCHAR(255)          NULL,
    total_index       VARCHAR(255)          NULL,
    wave_height_min   FLOAT                 NULL,
    wave_height_max   FLOAT                 NULL,
    sea_temp_min      FLOAT                 NULL,
    sea_temp_max      FLOAT                 NULL,
    current_speed_min FLOAT                 NULL,
    current_speed_max FLOAT                 NULL,
    CONSTRAINT pk_scuba_forecast PRIMARY KEY (id)
);

CREATE TABLE spot_score
(
    spot_id BIGINT NOT NULL,
    score   DOUBLE NULL,
    CONSTRAINT pk_spot_score PRIMARY KEY (spot_id)
);

CREATE TABLE spot_view_quartile
(
    spot_id        BIGINT   NOT NULL,
    month_quartile INT      NULL,
    week_quartile  INT      NULL,
    updated_at     datetime NULL,
    CONSTRAINT pk_spot_view_quartile PRIMARY KEY (spot_id)
);

CREATE TABLE spot_view_stats
(
    spot_id    BIGINT NOT NULL,
    view_date  date   NOT NULL,
    view_count INT    NOT NULL,
    CONSTRAINT pk_spot_view_stats PRIMARY KEY (spot_id, view_date)
);

CREATE TABLE surfing_forecast
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    created_at    datetime              NOT NULL,
    updated_at    datetime              NULL,
    spot_id       BIGINT                NOT NULL,
    forecast_date date                  NOT NULL,
    time_period   VARCHAR(10)           NOT NULL,
    wave_height   FLOAT                 NULL,
    wave_period   FLOAT                 NULL,
    wind_speed    FLOAT                 NULL,
    sea_temp      FLOAT                 NULL,
    total_index   VARCHAR(255)          NULL,
    uv_index      FLOAT                 NULL,
    CONSTRAINT pk_surfing_forecast PRIMARY KEY (id)
);

CREATE TABLE tags
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    created_at datetime              NOT NULL,
    updated_at datetime              NULL,
    meeting_id BIGINT                NOT NULL,
    content    VARCHAR(255)          NULL,
    CONSTRAINT pk_tags PRIMARY KEY (id)
);

ALTER TABLE scuba_forecast
    ADD CONSTRAINT uc_32af3e8aea14cf0a1d356667d UNIQUE (spot_id, forecast_date, time_period);

ALTER TABLE surfing_forecast
    ADD CONSTRAINT uc_bdc6542fee0fbd104d9b45b61 UNIQUE (spot_id, forecast_date, time_period);

ALTER TABLE blacklisted_refresh_tokens
    ADD CONSTRAINT uc_blacklisted_refresh_tokens_jti UNIQUE (jti);

ALTER TABLE fishing_forecast
    ADD CONSTRAINT uc_c5922890e701e91cf48ec5dbd UNIQUE (spot_id, forecast_date, time_period);

ALTER TABLE mudflat_forecast
    ADD CONSTRAINT uc_dbfa549ad8752badf8fa14a6d UNIQUE (spot_id, forecast_date);

ALTER TABLE fishing_targets
    ADD CONSTRAINT uc_fishing_targets_name UNIQUE (name);

ALTER TABLE jellyfish_species
    ADD CONSTRAINT uc_jellyfish_species_name UNIQUE (name);

ALTER TABLE members
    ADD CONSTRAINT uc_members_email UNIQUE (email);

ALTER TABLE members
    ADD CONSTRAINT uc_members_nickname UNIQUE (nickname);

ALTER TABLE outdoor_spots
    ADD CONSTRAINT uk_lat_lon_category UNIQUE (latitude, longitude, category);

CREATE INDEX idx_blacklisted_refresh_tokens_jti ON blacklisted_refresh_tokens (jti);

CREATE INDEX idx_lat_lon ON outdoor_spots (latitude, longitude);

CREATE INDEX idx_point ON outdoor_spots (geo_point);