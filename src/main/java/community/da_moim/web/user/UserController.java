package community.da_moim.web.user;

import community.da_moim.service.user.UserService;
import community.da_moim.web.user.dto.request.UserSaveDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
@RequestMapping("/api/users")
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<Void> join(@Valid @RequestBody UserSaveDto userSaveDto, UriComponentsBuilder uriComponentsBuilder) {
        Long userId = userService.join(userSaveDto);
        URI uri = uriComponentsBuilder.path("/api/users/{userId}")
                .buildAndExpand(userId)
                .toUri();
        return ResponseEntity
                .created(uri)
                .build();
    }
}
