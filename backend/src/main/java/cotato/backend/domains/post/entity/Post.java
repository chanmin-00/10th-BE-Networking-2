package cotato.backend.domains.post.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "post_id")
	private Long id;

	// 제목
	@Column(name = "title", nullable = false)
	private String title;

	// 내용
	@Column(name = "content", nullable = false)
	private String content;

	// 작성자
	@Column(name = "name", nullable = false)
	private String name;

	// 조회수, 기본값 0
	@Column(name = "views")
	private long views = 0L;

	// 조회수 증가
	public void increaseViews() {
		this.views++;
	}

	// 제목 설정
	public void setTitle(String title) {
		this.title = title;
	}

	// 내용 설정
	public void setContent(String content) {
		this.content = content;
	}

	// 작성자 설정
	public void setName(String name) {
		this.name = name;
	}

}
