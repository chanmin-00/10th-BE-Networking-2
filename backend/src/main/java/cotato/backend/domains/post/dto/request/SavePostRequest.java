package cotato.backend.domains.post.dto.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

// 단일 게시글 저장 요청
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SavePostRequest {

	// 게시글 제목
	private String title;

	// 게시글 내용
	private String content;

	// 게시글 작성자
	private String name;

}
