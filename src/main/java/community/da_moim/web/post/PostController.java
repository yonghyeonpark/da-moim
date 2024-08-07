package community.da_moim.web.post;

import community.da_moim.service.auth.dto.UserDetailsImpl;
import community.da_moim.service.post.PostService;
import community.da_moim.web.post.dto.request.PostSaveDto;
import community.da_moim.web.post.dto.request.PostUpdateDto;
import community.da_moim.web.post.dto.response.PostAlarmDto;
import community.da_moim.web.post.dto.response.PostShowDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/posts")
@RestController
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<Void> post(
            @Valid @RequestBody PostSaveDto postSaveDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            UriComponentsBuilder uriComponentsBuilder
    ) {
        Long postId = postService.post(postSaveDto, userDetails.getUsername());
        URI uri = uriComponentsBuilder.path("/api/posts/{postId}")
                .buildAndExpand(postId)
                .toUri();
        return ResponseEntity
                .created(uri)
                .build();
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostShowDto> showPostDetail(@PathVariable Long postId) {
        return ResponseEntity
                .ok(postService.getPostDetail(postId));
    }

    @GetMapping
    public ResponseEntity<List<PostShowDto>> showPostsDetail() {
        return ResponseEntity
                .ok(postService.getPostsDetail());
    }

    @PostMapping("/search")
    public ResponseEntity<List<PostShowDto>> searchPostBy(@RequestParam String titleKeyword) {
        return ResponseEntity
                .ok(postService.getPostsDetailByTitleKeyword(titleKeyword));
    }

    @PutMapping("/{postId}")
    public ResponseEntity<PostAlarmDto> updatePost(
            @RequestBody PostUpdateDto postUpdateDto,
            @PathVariable Long postId
    ) {
        return ResponseEntity
                .ok(postService.update(postUpdateDto, postId));
    }

    @DeleteMapping("{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable Long postId) {
        postService.delete(postId);
        return ResponseEntity
                .noContent()
                .build();
    }
}
