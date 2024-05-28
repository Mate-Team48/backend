package teamproject.project.service;

import java.util.List;
import org.springframework.data.domain.Pageable;
import teamproject.project.dto.fundraising.CreateFundraisingRequestDto;
import teamproject.project.dto.fundraising.FundraisingDto;
import teamproject.project.model.User;

public interface FundraisingService {
    List<FundraisingDto> getFundraisings(boolean isActive, String category, Pageable pageable);

    FundraisingDto getFundraisingById(long fundraisingId);

    FundraisingDto createFundraising(CreateFundraisingRequestDto requestDto, User user);

    FundraisingDto closeFundraisingById(long fundraisingId, User user);
}
