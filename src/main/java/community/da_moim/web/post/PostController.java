package community.da_moim.web.post;

import community.da_moim.service.auth.dto.UserDetailsImpl;
import community.da_moim.service.post.PostService;
import community.da_moim.web.post.dto.request.PostSaveDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
@RequestMapping("/api/posts")
@RestController
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<Void> post(
            @Valid @RequestBody PostSaveDto postSaveDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            UriComponentsBuilder uriComponentsBuilder) {
        Long postId = postService.post(postSaveDto, userDetails.getUsername());
        URI uri = uriComponentsBuilder.path("/api/posts/{postId}")
                .buildAndExpand(postId)
                .toUri();
        return ResponseEntity
                .created(uri)
                .build();
    }
}
