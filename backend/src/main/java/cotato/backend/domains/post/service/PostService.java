package cotato.backend.domains.post.service;

import static cotato.backend.common.exception.ErrorCode.*;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cotato.backend.common.excel.ExcelUtils;
import cotato.backend.common.exception.ApiException;
import cotato.backend.domains.post.converter.PostConverter;
import cotato.backend.domains.post.dto.request.SavePostRequest;
import cotato.backend.domains.post.dto.response.GetPostPreviewListResponse;
import cotato.backend.domains.post.dto.response.GetPostPreviewResponse;
import cotato.backend.domains.post.dto.response.GetPostResponse;
import cotato.backend.domains.post.entity.Post;
import cotato.backend.domains.post.repository.PostRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class PostService {

	private final PostRepository postRepository;

	// 로컬 파일 경로로부터 엑셀 파일을 읽어 Post 엔터티로 변환하고 저장
	@Transactional
	public void saveEstatesByExcel(String filePath) {
		if (filePath == null) {
			throw ApiException.from(INVALID_PARAMETER);
		}

		try {
			// 엑셀 파일을 읽어 데이터 프레임 형태로 변환
			List<Post> postList = ExcelUtils.parseExcelFile(filePath).stream().map(row -> {
				String title = row.get("title");
				String content = row.get("content");
				String name = row.get("name");

				return PostConverter.toPostEntity(title, content, name);
			}).toList();

			// 게시글 목록 저장
			postRepository.saveAllByJdbcTemplate(postList);

		} catch (Exception e) {
			log.error("Failed to save estates by excel", e);
			throw ApiException.from(SAVE_EXCEL_FILE_FAILED);
		}
	}

	// 단일 게시글 저장
	@Transactional
	public void savePost(SavePostRequest request) {
		if (request.getTitle() == null || request.getContent() == null || request.getName() == null) {
			throw ApiException.from(INVALID_PARAMETER);
		}

		Post post = PostConverter.toPostEntity(request);

		postRepository.save(post);
	}

	// 단일 게시글 조회
	public GetPostResponse getPostById(Long id) {

		// 게시글이 존재하지 않을 경우 예외 처리
		Post post = postRepository.findById(id).orElseThrow(() -> ApiException.from(POST_NOT_FOUND));

		// 조회수 증가
		post.increaseViews();

		// 조회수 증가된 게시글 저장
		postRepository.save(post);

		// DTO로 변환하여 반환
		return PostConverter.toGetPostResponse(post);
	}

	// 게시글 목록 조회, 조회수 내림차순 정렬
	public GetPostPreviewListResponse getPostPreviewList(int page) {
		if (page < 0) {
			throw ApiException.from(INVALID_PARAMETER);
		}

		Pageable pageable = Pageable.ofSize(10).withPage(page);
		Page<Post> postPage = postRepository.findAllByOrderByViewsDesc(pageable);

		List<GetPostPreviewResponse> postPreviewList = postPage.getContent()
			.stream()
			.map(PostConverter::toGetPostPreviewResponse)
			.toList();

		return PostConverter.toGetPostPreviewListResponse(postPreviewList, postPage.getTotalPages(),
			postPage.getNumber());
	}

	// 단일 게시글 삭제
	@Transactional
	public void deletePost(Long id) {
		Post post = postRepository.findById(id).orElseThrow(() -> ApiException.from(POST_NOT_FOUND));
		postRepository.delete(post);
	}

}
