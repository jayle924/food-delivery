package fooddelivery.infra;

import fooddelivery.domain.*;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import java.util.List;
import java.util.Optional;

@RepositoryRestResource(collectionResourceRel="myPages", path="myPages")
public interface MyPageRepository extends PagingAndSortingRepository<MyPage, Long> {
    List<MyPage> findAllById(Long id);
}
