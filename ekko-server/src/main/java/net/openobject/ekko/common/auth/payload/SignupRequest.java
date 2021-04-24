package net.openobject.ekko.common.auth.payload;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;
import net.openobject.ekko.user.entity.UserRole;

/**
 * SignupRequest.java
 * <br/>
 * 사용자 등록 VO
 * 
 * @author : SeHoon
 * @version : 1.0
 */
@Data
public class SignupRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String userId;
 
    @NotBlank
    @Size(max = 50)
    @Email
    private String userEmailAddr;
    
    private Set<UserRole> role;
    
    private Set<String> strRole;
    
    @NotBlank
    @Size(min = 6, max = 40)
    private String password;
  
}
