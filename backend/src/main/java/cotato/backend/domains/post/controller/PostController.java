package cotato.backend.domains.post.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cotato.backend.common.dto.DataResponse;
import cotato.backend.domains.post.service.PostService;
import cotato.backend.domains.post.dto.request.SavePostsByExcelRequest;
import cotato.backend.domains.post.dto.request.SavePostRequest;
import cotato.backend.domains.post.dto.response.GetPostPreviewListResponse;
import cotato.backend.domains.post.dto.response.GetPostResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

	private final PostService postService;

	// 엑셀 파일로부터 게시글 저장 API
	@Operation(summary = "엑셀 파일로부터 게시글 저장", description = "엑셀 파일로부터 게시글을 저장합니다.")
	@PostMapping("/excel")
	public ResponseEntity<DataResponse<Void>> savePostsByExcel(@RequestBody SavePostsByExcelRequest request) {
		postService.saveEstatesByExcel(request.getPath());

		return ResponseEntity.ok(DataResponse.ok());
	}

	// 단일 게시글 저장 API
	@Operation(summary = "게시글 저장", description = "단일 게시글을 저장합니다.")
	@PostMapping("/create")
	public ResponseEntity<DataResponse<Void>> createPost(@RequestBody SavePostRequest request) {
		postService.savePost(request);

		return ResponseEntity.ok(DataResponse.ok());
	}

	// 단일 게시글 조회 API
	@Operation(summary = "게시글 조회", description = "게시글을 조회합니다.")
	@GetMapping("/{id}")
	public ResponseEntity<DataResponse<GetPostResponse>> getPostById(@PathVariable Long id) {
		return ResponseEntity.ok(DataResponse.from(postService.getPostById(id)));
	}

	// 게시글 목록 조회 API
	@Operation(summary = "게시글 목록 조회", description = "게시글 목록을 조회합니다.")
	@GetMapping
	public ResponseEntity<DataResponse<GetPostPreviewListResponse>> getPosts(int page) {
		return ResponseEntity.ok(DataResponse.from(postService.getPostPreviewList(page)));
	}

	// 단일 게시글 삭제 API
	@Operation(summary = "게시글 삭제", description = "게시글을 삭제합니다.")
	@DeleteMapping("/{id}")
	public ResponseEntity<DataResponse<Void>> deletePost(@PathVariable Long id) {
		postService.deletePost(id);
		return ResponseEntity.ok(DataResponse.ok());
	}
}
