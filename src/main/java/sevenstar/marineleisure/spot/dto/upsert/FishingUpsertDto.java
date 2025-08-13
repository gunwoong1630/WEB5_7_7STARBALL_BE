package sevenstar.marineleisure.spot.dto.upsert;

import java.time.LocalDate;

public record FishingUpsertDto(
	Long spotId,
	Long targetId,
	LocalDate forecastDate,
	String timePeriod,
	String tide,
	String totalIndex,
	Float waveHeightMin,
	Float waveHeightMax,
	Float seaTempMin,
	Float seaTempMax,
	Float airTempMin,
	Float airTempMax,
	Float currentSpeedMin,
	Float currentSpeedMax,
	Float windSpeedMin,
	Float windSpeedMax
) {
}
