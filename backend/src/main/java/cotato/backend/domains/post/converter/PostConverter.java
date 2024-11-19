package cotato.backend.domains.post.converter;

import java.util.List;

import org.springframework.stereotype.Component;

import cotato.backend.domains.post.entity.Post;
import cotato.backend.domains.post.dto.request.SavePostRequest;
import cotato.backend.domains.post.dto.response.GetPostPreviewListResponse;
import cotato.backend.domains.post.dto.response.GetPostPreviewResponse;
import cotato.backend.domains.post.dto.response.GetPostResponse;

@Component
public class PostConverter {

	// SavePostsRequest -> Post로 변환
	public static Post toPostEntity(SavePostRequest request) {
		return Post.builder()
			.title(request.getTitle())
			.content(request.getContent())
			.name(request.getName())
			.build();
	}

	// title, content, name -> Post로 변환
	public static Post toPostEntity(String title, String content, String name) {
		return Post.builder()
			.title(title)
			.content(content)
			.name(name)
			.build();
	}

	// Post -> GetPostResponse로 변환
	public static GetPostResponse toGetPostResponse(Post post) {
		return GetPostResponse.builder()
			.title(post.getTitle())
			.content(post.getContent())
			.name(post.getName())
			.views(post.getViews())
			.build();
	}

	// Post -> GetPostPreviewResponse로 변환
	public static GetPostPreviewResponse toGetPostPreviewResponse(Post post) {
		return GetPostPreviewResponse.builder()
			.id(post.getId())
			.title(post.getTitle())
			.name(post.getName())
			.build();
	}

	// List<GetPostPreviewResponse> -> GetPostPreviewListResponse로 변환
	public static GetPostPreviewListResponse toGetPostPreviewListResponse(List<GetPostPreviewResponse> postList,
		int totalPage, int currentPage) {
		return GetPostPreviewListResponse.builder()
			.postList(postList)
			.totalPage(totalPage)
			.currentPage(currentPage)
			.build();
	}
}
