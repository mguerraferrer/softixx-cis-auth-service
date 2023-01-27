package mx.softixx.cis.cloud.auth.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.softixx.cis.cloud.auth.persistence.model.AccountVerification;

/**
 * Repository : AccountVerification
 * 
 * @author Maikel Guerra Ferrer - mguerraferrer@gmail.com
 * 
 */
public interface AccountVerificationRepository extends JpaRepository<AccountVerification, Long> {

	/**
	 * Returns the AccountVerification that exactly matches the given token
	 * 
	 * @param token String
	 * @return AccountVerification or null
	 */
	AccountVerification findByTokenAndActiveTrue(String token);

	/**
	 * Returns the AccountVerification associated with a userId if
	 * {@link AccountVerification#isActive()} is false
	 * 
	 * @param userId Long
	 * @return AccountVerification or null
	 */
	AccountVerification findByUserIdAndActiveTrue(Long userId);

}