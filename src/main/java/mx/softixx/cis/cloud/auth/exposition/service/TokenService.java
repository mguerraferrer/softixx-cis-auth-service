package mx.softixx.cis.cloud.auth.exposition.service;

import mx.softixx.cis.common.user.payload.TokenResponse;

public interface TokenService {
	
	TokenResponse generateToken(String email, String hash);
	
}