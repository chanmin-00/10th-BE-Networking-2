package cotato.backend.domains.post.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 게시글 상세 조회	응답
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetPostResponse {

	// 게시글 제목
	private String title;

	// 게시글 내용
	private String content;

	// 게시글 작성자
	private String name;

	// 조회수
	private Long views;
}
