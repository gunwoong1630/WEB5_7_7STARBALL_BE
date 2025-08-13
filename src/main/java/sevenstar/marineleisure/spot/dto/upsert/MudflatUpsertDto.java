package sevenstar.marineleisure.spot.dto.upsert;

import java.time.LocalDate;
import java.time.LocalTime;

public record MudflatUpsertDto(Long spotId, LocalDate forecastDate, LocalTime startTime, LocalTime endTime,
							   Float airTempMin, Float airTempMax, Float windSpeedMin, Float windSpeedMax,
							   String weather, String totalIndex) {
}
