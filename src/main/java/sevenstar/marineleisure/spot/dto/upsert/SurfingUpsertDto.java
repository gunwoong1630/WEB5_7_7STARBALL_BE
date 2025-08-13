package sevenstar.marineleisure.spot.dto.upsert;

import java.time.LocalDate;

public record SurfingUpsertDto(
	Long spotId,
	LocalDate forecastDate,
	String timePeriod,
	Float waveHeight,
	Float wavePeriod,
	Float windSpeed,
	Float seaTemp,
	String totalIndex
) {
}
