package sevenstar.marineleisure.spot.dto.upsert;

import java.time.LocalDate;

public record ScubaUpsertDto(
	Long spotId,
	LocalDate forecastDate,
	String timePeriod,
	String tide,
	String totalIndex,
	Float waveHeightMin,
	Float waveHeightMax,
	Float seaTempMin,
	Float seaTempMax,
	Float currentSpeedMin,
	Float currentSpeedMax
) {
}
