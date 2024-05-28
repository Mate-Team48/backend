package teamproject.project.repository;

import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;
import teamproject.project.model.*;

import java.util.*;

public interface FundraisingRepository extends JpaRepository<Fundraising, Long> {
    @Query("SELECT f FROM Fundraising f WHERE f.user.id = :userId AND f.isFinished = :isActive AND f.sphere = :category")
    List<Fundraising> findAllByUserIdAndIsActiveAndCategory(@Param("userId") long userId,
                                                            @Param("isActive") boolean isActive,
                                                            @Param("category") Fundraising.Category category,
                                                            Pageable pageable);
}
