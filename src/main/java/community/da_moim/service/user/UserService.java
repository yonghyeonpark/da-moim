package community.da_moim.service.user;

import community.da_moim.domain.user.UserRepository;
import community.da_moim.util.mapper.UserMapper;
import community.da_moim.web.user.dto.request.UserSaveDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public Long join(UserSaveDto userSaveDto) {
        return userRepository
                .save(UserMapper.toEntity(userSaveDto, passwordEncoder.encode(userSaveDto.getPassword())))
                .getId();
    }
}
