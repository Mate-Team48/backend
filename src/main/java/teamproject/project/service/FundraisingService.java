package teamproject.project.service;

import org.springframework.data.domain.*;
import teamproject.project.dto.fundraising.*;
import teamproject.project.model.*;

import java.util.*;

public interface FundraisingService {
    List<FundraisingDto> getFundraisings(boolean isActive, String category, Pageable pageable);

    FundraisingDto getFundraisingById(long fundraisingId);

    FundraisingDto createFundraising(CreateFundraisingRequestDto requestDto, User user);

    FundraisingDto closeFundraisingById(long fundraisingId, User user);
}
