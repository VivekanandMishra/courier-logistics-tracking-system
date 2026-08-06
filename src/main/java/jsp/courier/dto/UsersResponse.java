package jsp.courier.dto;

import jsp.courier.Role;
import lombok.Data;

@Data
public class UsersResponse {

    private Long id;
    private String username;
    private String email;
    private Role role;
    private boolean enabled;
}