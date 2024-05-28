package teamproject.project.repository;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import teamproject.project.model.Fundraising;

public interface FundraisingRepository extends JpaRepository<Fundraising, Long> {
    @Query("SELECT f FROM Fundraising f "
            + "WHERE f.isFinished = :isActive AND f.sphere = :category")
    List<Fundraising> findAllByIsActiveAndCategory(boolean isActive,
                                                   Fundraising.Category category,
                                                   Pageable pageable);

    @Query("SELECT f FROM Fundraising f "
            + "WHERE f.user.id = :userId AND f.isFinished = :isActive AND f.sphere = :category")
    List<Fundraising> findAllByUserIdAndIsActiveAndCategory(long userId,
                                                            boolean isActive,
                                                            Fundraising.Category category,
                                                            Pageable pageable);
}
