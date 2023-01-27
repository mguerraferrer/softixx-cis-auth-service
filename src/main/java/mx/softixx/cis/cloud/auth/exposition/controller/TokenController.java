package mx.softixx.cis.cloud.auth.exposition.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.val;
import mx.softixx.cis.cloud.auth.exposition.observavility.TokenObservation;
import mx.softixx.cis.common.user.payload.TokenRequest;
import mx.softixx.cis.common.user.payload.TokenResponse;

@RestController
@RequestMapping("/api/v1/auth")
public class TokenController {

	private final AuthenticationManager authenticationManager;
	private final TokenObservation tokenObservation;

	public TokenController(AuthenticationManager authenticationManager, TokenObservation tokenObservation) {
		this.authenticationManager = authenticationManager;
		this.tokenObservation = tokenObservation;
	}

	@GetMapping
	public ResponseEntity<String> secure() {
		return ResponseEntity.ok("This is a secure resource");
	}

	@PostMapping("/token")
	public ResponseEntity<TokenResponse> findByEmail(@RequestBody TokenRequest request) {
		val authenticationToken = new UsernamePasswordAuthenticationToken(request.client(), request.secret());
		authenticationManager.authenticate(authenticationToken);

		val tokenResponse = tokenObservation.createToken(request.email(), request.hash());
		return ResponseEntity.ok(tokenResponse);
	}

}