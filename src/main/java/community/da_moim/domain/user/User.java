package community.da_moim.domain.user;

import community.da_moim.domain.BaseTimeEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String loginId;
    private String password;
    private String nickname;
    private String email;
    private String phoneNumber;

    public User(String loginId, String password, String nickname, String email, String phoneNumber) {
        this.loginId = loginId;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
