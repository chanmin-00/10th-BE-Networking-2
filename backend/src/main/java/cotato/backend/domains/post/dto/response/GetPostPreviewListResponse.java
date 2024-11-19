package cotato.backend.domains.post.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 게시글 미리보기 리스트 조회 응답
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetPostPreviewListResponse {

	// 게시글 미리보기 리스트
	private List<GetPostPreviewResponse> postList;

	// 전체 페이지 수
	private int totalPage;

	// 현재 페이지
	private int currentPage;
}
