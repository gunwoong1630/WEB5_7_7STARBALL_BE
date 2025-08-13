package sevenstar.marineleisure.spot.repository;

import java.sql.Date;
import java.sql.Time;
import java.sql.Types;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import sevenstar.marineleisure.spot.dto.upsert.FishingUpsertDto;
import sevenstar.marineleisure.spot.dto.upsert.MudflatUpsertDto;
import sevenstar.marineleisure.spot.dto.upsert.ScubaUpsertDto;
import sevenstar.marineleisure.spot.dto.upsert.SurfingUpsertDto;

@Repository
@RequiredArgsConstructor
public class ActivityJdbcBatchUpsertRepository {
	private final JdbcTemplate jdbcTemplate;

	private static final int BATCH_SIZE = 100;
	private static final String FISHING_UPSERT_SQL = """
		INSERT INTO fishing_forecast (
			spot_id, target_id, forecast_date, time_period, tide, total_index,
			wave_height_min, wave_height_max, sea_temp_min, sea_temp_max,
			air_temp_min, air_temp_max, current_speed_min, current_speed_max,
			wind_speed_min, wind_speed_max, created_at, updated_at
		) VALUES (?, ?, ?, ?, ?, ?,
				  ?, ?, ?, ?,
				  ?, ?, ?, ?,
				  ?, ?, NOW(), NOW())
		ON DUPLICATE KEY UPDATE
			tide = IF(VALUES(tide) <> tide, VALUES(tide), tide),
			total_index = IF(VALUES(total_index) <> total_index, VALUES(total_index), total_index),
			wave_height_min = IF(VALUES(wave_height_min) <> wave_height_min, VALUES(wave_height_min), wave_height_min),
			wave_height_max = IF(VALUES(wave_height_max) <> wave_height_max, VALUES(wave_height_max), wave_height_max),
			sea_temp_min = IF(VALUES(sea_temp_min) <> sea_temp_min, VALUES(sea_temp_min), sea_temp_min),
			sea_temp_max = IF(VALUES(sea_temp_max) <> sea_temp_max, VALUES(sea_temp_max), sea_temp_max),
			air_temp_min = IF(VALUES(air_temp_min) <> air_temp_min, VALUES(air_temp_min), air_temp_min),
			air_temp_max = IF(VALUES(air_temp_max) <> air_temp_max, VALUES(air_temp_max), air_temp_max),
			current_speed_min = IF(VALUES(current_speed_min) <> current_speed_min, VALUES(current_speed_min), current_speed_min),
			current_speed_max = IF(VALUES(current_speed_max) <> current_speed_max, VALUES(current_speed_max), current_speed_max),
			wind_speed_min = IF(VALUES(wind_speed_min) <> wind_speed_min, VALUES(wind_speed_min), wind_speed_min),
			wind_speed_max = IF(VALUES(wind_speed_max) <> wind_speed_max, VALUES(wind_speed_max), wind_speed_max),
			updated_at = IF(
				VALUES(tide) <> tide OR
				VALUES(total_index) <> total_index OR
				VALUES(wave_height_min) <> wave_height_min OR
				VALUES(wave_height_max) <> wave_height_max OR
				VALUES(sea_temp_min) <> sea_temp_min OR
				VALUES(sea_temp_max) <> sea_temp_max OR
				VALUES(air_temp_min) <> air_temp_min OR
				VALUES(air_temp_max) <> air_temp_max OR
				VALUES(current_speed_min) <> current_speed_min OR
				VALUES(current_speed_max) <> current_speed_max OR
				VALUES(wind_speed_min) <> wind_speed_min OR
				VALUES(wind_speed_max) <> wind_speed_max,
				NOW(),
				updated_at
			)
		""";
	private static final String MUDFLAT_UPSERT_SQL = """
		INSERT INTO mudflat_forecast (
		    spot_id, forecast_date, start_time, end_time, 
		    air_temp_min, air_temp_max, wind_speed_min, wind_speed_max,
		    weather, total_index, created_at, updated_at
		) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW(), NOW())
		ON DUPLICATE KEY UPDATE
		    start_time = IF(VALUES(start_time) <> start_time, VALUES(start_time), start_time),
		    end_time = IF(VALUES(end_time) <> end_time, VALUES(end_time), end_time),
		    air_temp_min = IF(VALUES(air_temp_min) <> air_temp_min, VALUES(air_temp_min), air_temp_min),
		    air_temp_max = IF(VALUES(air_temp_max) <> air_temp_max, VALUES(air_temp_max), air_temp_max),
		    wind_speed_min = IF(VALUES(wind_speed_min) <> wind_speed_min, VALUES(wind_speed_min), wind_speed_min),
		    wind_speed_max = IF(VALUES(wind_speed_max) <> wind_speed_max, VALUES(wind_speed_max), wind_speed_max),
		    weather = IF(VALUES(weather) <> weather, VALUES(weather), weather),
		    total_index = IF(VALUES(total_index) <> total_index, VALUES(total_index), total_index),
		    updated_at = IF(
		        VALUES(start_time) <> start_time OR
		        VALUES(end_time) <> end_time OR
		        VALUES(air_temp_min) <> air_temp_min OR
		        VALUES(air_temp_max) <> air_temp_max OR
		        VALUES(wind_speed_min) <> wind_speed_min OR
		        VALUES(wind_speed_max) <> wind_speed_max OR
		        VALUES(weather) <> weather OR
		        VALUES(total_index) <> total_index,
		        NOW(),
		        updated_at
		    )
		""";
	private static final String SURFING_UPSERT_SQL = """
		INSERT INTO surfing_forecast (
		    spot_id, forecast_date, time_period, wave_height, wave_period,
		    wind_speed, sea_temp, total_index, created_at, updated_at
		) VALUES (?, ?, ?, ?, ?, ?, ?, ?, NOW(), NOW())
		ON DUPLICATE KEY UPDATE
		    wave_height = IF(VALUES(wave_height) <> wave_height, VALUES(wave_height), wave_height),
		    wave_period = IF(VALUES(wave_period) <> wave_period, VALUES(wave_period), wave_period),
		    wind_speed = IF(VALUES(wind_speed) <> wind_speed, VALUES(wind_speed), wind_speed),
		    sea_temp = IF(VALUES(sea_temp) <> sea_temp, VALUES(sea_temp), sea_temp),
		    total_index = IF(VALUES(total_index) <> total_index, VALUES(total_index), total_index),
		    updated_at = IF(
		        VALUES(wave_height) <> wave_height OR
		        VALUES(wave_period) <> wave_period OR
		        VALUES(wind_speed) <> wind_speed OR
		        VALUES(sea_temp) <> sea_temp OR
		        VALUES(total_index) <> total_index,
		        NOW(),
		        updated_at
		    )
		""";
	private static final String SCUBA_UPSERT_SQL = """
		INSERT INTO scuba_forecast (
		    spot_id, forecast_date, time_period, tide, total_index,
		    wave_height_min, wave_height_max, sea_temp_min, sea_temp_max,
		    current_speed_min, current_speed_max, created_at, updated_at
		) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW(), NOW())
		ON DUPLICATE KEY UPDATE
		    tide = IF(VALUES(tide) <> tide, VALUES(tide), tide),
		    total_index = IF(VALUES(total_index) <> total_index, VALUES(total_index), total_index),
		    wave_height_min = IF(VALUES(wave_height_min) <> wave_height_min, VALUES(wave_height_min), wave_height_min),
		    wave_height_max = IF(VALUES(wave_height_max) <> wave_height_max, VALUES(wave_height_max), wave_height_max),
		    sea_temp_min = IF(VALUES(sea_temp_min) <> sea_temp_min, VALUES(sea_temp_min), sea_temp_min),
		    sea_temp_max = IF(VALUES(sea_temp_max) <> sea_temp_max, VALUES(sea_temp_max), sea_temp_max),
		    current_speed_min = IF(VALUES(current_speed_min) <> current_speed_min, VALUES(current_speed_min), current_speed_min),
		    current_speed_max = IF(VALUES(current_speed_max) <> current_speed_max, VALUES(current_speed_max), current_speed_max),
		    updated_at = IF(
		        VALUES(tide) <> tide OR
		        VALUES(total_index) <> total_index OR
		        VALUES(wave_height_min) <> wave_height_min OR
		        VALUES(wave_height_max) <> wave_height_max OR
		        VALUES(sea_temp_min) <> sea_temp_min OR
		        VALUES(sea_temp_max) <> sea_temp_max OR
		        VALUES(current_speed_min) <> current_speed_min OR
		        VALUES(current_speed_max) <> current_speed_max,
		        NOW(),
		        updated_at
		    )
		""";

	public void batchUpsertFishing(List<FishingUpsertDto> batchData) {
		jdbcTemplate.batchUpdate(
			FISHING_UPSERT_SQL,
			batchData,
			BATCH_SIZE, // batch size
			(ps, dto) -> {
				ps.setLong(1, dto.spotId());
				if (dto.targetId() == null) {
					ps.setNull(2, Types.BIGINT);
				} else {
					ps.setLong(2, dto.targetId());
				}
				ps.setDate(3, Date.valueOf(dto.forecastDate()));
				ps.setString(4, dto.timePeriod());
				ps.setString(5, dto.tide());
				ps.setString(6, dto.totalIndex());
				ps.setObject(7, dto.waveHeightMin(), Types.FLOAT);
				ps.setObject(8, dto.waveHeightMax(), Types.FLOAT);
				ps.setObject(9, dto.seaTempMin(), Types.FLOAT);
				ps.setObject(10, dto.seaTempMax(), Types.FLOAT);
				ps.setObject(11, dto.airTempMin(), Types.FLOAT);
				ps.setObject(12, dto.airTempMax(), Types.FLOAT);
				ps.setObject(13, dto.currentSpeedMin(), Types.FLOAT);
				ps.setObject(14, dto.currentSpeedMax(), Types.FLOAT);
				ps.setObject(15, dto.windSpeedMin(), Types.FLOAT);
				ps.setObject(16, dto.windSpeedMax(), Types.FLOAT);
			}
		);
	}

	public void batchUpsertMudflat(List<MudflatUpsertDto> batchData) {
		jdbcTemplate.batchUpdate(
			MUDFLAT_UPSERT_SQL,
			batchData,
			BATCH_SIZE,
			(ps, dto) -> {
				ps.setLong(1, dto.spotId());
				ps.setDate(2, Date.valueOf(dto.forecastDate()));
				ps.setTime(3, Time.valueOf(dto.startTime()));
				ps.setTime(4, Time.valueOf(dto.endTime()));
				ps.setObject(5, dto.airTempMin(), Types.FLOAT);
				ps.setObject(6, dto.airTempMax(), Types.FLOAT);
				ps.setObject(7, dto.windSpeedMin(), Types.FLOAT);
				ps.setObject(8, dto.windSpeedMax(), Types.FLOAT);
				ps.setString(9, dto.weather());
				ps.setString(10, dto.totalIndex());
			}
		);
	}

	public void batchUpsertSurfing(List<SurfingUpsertDto> batchData) {
		jdbcTemplate.batchUpdate(
			SURFING_UPSERT_SQL,
			batchData,
			BATCH_SIZE,
			(ps, dto) -> {
				ps.setLong(1, dto.spotId());
				ps.setDate(2, Date.valueOf(dto.forecastDate()));
				ps.setString(3, dto.timePeriod());
				ps.setObject(4, dto.waveHeight(), Types.FLOAT);
				ps.setObject(5, dto.wavePeriod(), Types.FLOAT);
				ps.setObject(6, dto.windSpeed(), Types.FLOAT);
				ps.setObject(7, dto.seaTemp(), Types.FLOAT);
				ps.setString(8, dto.totalIndex());
			}
		);
	}

	public void batchUpsertScuba(List<ScubaUpsertDto> batchData) {
		jdbcTemplate.batchUpdate(
			SCUBA_UPSERT_SQL,
			batchData,
			BATCH_SIZE,
			(ps, dto) -> {
				ps.setLong(1, dto.spotId());
				ps.setDate(2, Date.valueOf(dto.forecastDate()));
				ps.setString(3, dto.timePeriod());
				ps.setString(4, dto.tide());
				ps.setString(5, dto.totalIndex());
				ps.setObject(6, dto.waveHeightMin(), Types.FLOAT);
				ps.setObject(7, dto.waveHeightMax(), Types.FLOAT);
				ps.setObject(8, dto.seaTempMin(), Types.FLOAT);
				ps.setObject(9, dto.seaTempMax(), Types.FLOAT);
				ps.setObject(10, dto.currentSpeedMin(), Types.FLOAT);
				ps.setObject(11, dto.currentSpeedMax(), Types.FLOAT);
			}
		);
	}

}
