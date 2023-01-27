package mx.softixx.cis.cloud.auth.persistence.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import mx.softixx.cis.common.jpa.model.BaseEntity;

/**
 * Persistent class for entity stored in table "account_verification"
 *
 * @author Maikel Guerra Ferrer - mguerraferrer@gmail.com
 *
 */

@Entity
@Table(name = "account_verification", schema = "security")
@SequenceGenerator(name = "default_gen", sequenceName = "common.account_verification_id_seq", allocationSize = 1)
public class AccountVerification extends BaseEntity {
	
    @Column(name = "user_id")
    private Long userId;
	
    @Column(name = "token", nullable = false, length = 2147483647)
    private String token;

    @Column(name = "generated_at", nullable = false)
    private LocalDateTime generatedAt;

    @Column(name = "due_date", nullable = false)
    private LocalDate dueDate;

    @Column(name = "sent")
    private boolean sent;

    @Column(name = "valid")
    private boolean valid;

    @Column(name = "enabled")
    private boolean enabled;

    @Column(name = "enabled_at")
    private LocalDateTime enabledAt;

    @Column(name = "active")
    private boolean active;

    public AccountVerification() {
    }
    
    public AccountVerification(final Long userId, final String token, final Integer plusDays) {
		this.userId = userId;
		this.token = token;
		this.generatedAt = LocalDateTime.now();
		this.dueDate = LocalDate.now().plusDays(plusDays);
		this.valid = Boolean.TRUE;
		this.sent = Boolean.FALSE;
		this.enabled = Boolean.FALSE;
		this.active = Boolean.TRUE;
    }
    
    public AccountVerification(final String token, final Integer plusDays) {
		this.token = token;
		this.dueDate = LocalDate.now().plusDays(plusDays);
    }
    
    /* Getters and Setters */
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public LocalDateTime getGeneratedAt() {
		return generatedAt;
	}

	public void setGeneratedAt(LocalDateTime generatedAt) {
		this.generatedAt = generatedAt;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public boolean isSent() {
		return sent;
	}

	public void setSent(boolean sent) {
		this.sent = sent;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public LocalDateTime getEnabledAt() {
		return enabledAt;
	}

	public void setEnabledAt(LocalDateTime enabledAt) {
		this.enabledAt = enabledAt;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	/* toString */
    public String toString() { 
        var sb = new StringBuilder(); 
        sb.append("["); 
        sb.append(id);
        sb.append("]:"); 
        sb.append(token);
        sb.append("|");
        sb.append(generatedAt);
        sb.append("|");
        sb.append(dueDate);
        sb.append("|");
        sb.append(sent);
        sb.append("|");
        sb.append(valid);
        sb.append("|");
        sb.append(enabled);
        sb.append("|");
        sb.append(enabledAt);
        sb.append("|");
        sb.append(active);
        return sb.toString();
    }

}