package cotato.backend.domains.post.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cotato.backend.domains.post.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>, PostJdbcRepository {

	@Query("SELECT p FROM Post p ORDER BY p.views DESC")
	Page<Post> findAllByOrderByViewsDesc(Pageable pageable);

}
