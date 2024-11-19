package cotato.backend.domains.post.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 게시글 미리보기 항목 조회 응답
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetPostPreviewResponse {

	// 게시글 식별자
	private Long id;

	// 게시글 제목
	private String title;

	// 게시글 작성자
	private String name;

}
