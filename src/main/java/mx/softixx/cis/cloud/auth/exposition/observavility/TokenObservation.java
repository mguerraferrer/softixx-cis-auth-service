package mx.softixx.cis.cloud.auth.exposition.observavility;

import org.springframework.stereotype.Component;

import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import lombok.val;
import mx.softixx.cis.cloud.auth.exposition.service.TokenService;
import mx.softixx.cis.common.user.payload.TokenResponse;

@Component
public class TokenObservation {
	
	private final TokenService tokenService;
	private final ObservationRegistry observationRegistry;

	public TokenObservation(TokenService tokenService, ObservationRegistry observationRegistry) {
		this.tokenService = tokenService;
		this.observationRegistry = observationRegistry;
	}
	
	public TokenResponse createToken(String email, String hash) {
		val observationName = "TokenController#findByEmail";
		return Observation
				.createNotStarted(observationName, observationRegistry)
				.lowCardinalityKeyValue("email", email)
				.observe(() -> tokenService.generateToken(email, hash));
	}
	
}