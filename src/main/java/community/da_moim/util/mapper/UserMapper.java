package community.da_moim.util.mapper;

import community.da_moim.domain.user.User;
import community.da_moim.web.user.dto.request.UserSaveDto;

public class UserMapper {

    public static User toEntity(UserSaveDto userSaveDto, String encryptedPassword) {
        return new User(
                userSaveDto.getLoginId(),
                encryptedPassword,
                userSaveDto.getNickname(),
                userSaveDto.getEmail(),
                userSaveDto.getPhoneNumber()
        );
    }
}
