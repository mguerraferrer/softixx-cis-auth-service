package mx.softixx.cis.cloud.auth.exposition.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.stereotype.Service;

import lombok.val;
import lombok.extern.slf4j.Slf4j;
import mx.softixx.cis.common.user.exception.UserNotFoundException;
import mx.softixx.cis.common.user.payload.TokenResponse;
import mx.softixx.cis.common.user.persistence.model.Role;
import mx.softixx.cis.common.user.persistence.model.User;
import mx.softixx.cis.common.user.persistence.repository.UserRepository;

@Service
@Slf4j(topic = "TokenServiceImpl")
public class TokenServiceImpl implements TokenService {
	
	private final JwtEncoder encoder;
	private final JwtDecoder decoder;
	private final UserRepository userRepository;

	public TokenServiceImpl(JwtEncoder encoder, JwtDecoder decoder, UserRepository userRepository) {
		this.encoder = encoder;
		this.decoder = decoder;
		this.userRepository = userRepository;
	}
	
	@Override
	public TokenResponse generateToken(String email, String hash) {
		val user = userRepository.findByEmailAndHashAndActiveTrueAndBlockedAccessFalse(email, hash)
			 	 				 .orElseThrow(() -> new UserNotFoundException(email));
		
		val claims = getClaims(user);
		val token = this.encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
		val expireAt = getExpiresAt(token);
		
		return new TokenResponse(token, expireAt);
	}

	private final JwtClaimsSet getClaims(User user) {
		val now = Instant.now();
		val scope = getScope(user);
		
		return JwtClaimsSet
				.builder()
				.issuer("cis-webapp")
				.issuedAt(now)
				.expiresAt(now.plus(1, ChronoUnit.HOURS))
				.subject(user.getEmail())
				.claim("scope", scope)
				.build();
	}
	
	private final String getScope(User user) {
		return user.getRoles().stream().map(Role::getName).collect(Collectors.joining(" "));
	}
	
	private final Instant getExpiresAt(String token) {
		try {
			
			val jwt = this.decoder.decode(token);
			if (jwt != null) {
				return jwt.getExpiresAt();
			}
			
		} catch (JwtException e) {
			log.error("#getExpiresAt - JwtException {}", e.getMessage());
		}
		return null;
	}
	
}