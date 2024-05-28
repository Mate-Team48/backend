package teamproject.project.service.impl;

import lombok.*;
import org.springframework.data.domain.*;
import org.springframework.stereotype.*;
import teamproject.project.dto.fundraising.*;
import teamproject.project.mapper.*;
import teamproject.project.model.*;
import teamproject.project.repository.*;
import teamproject.project.service.*;

import java.util.*;

@Service
@RequiredArgsConstructor
public class FundraisingServiceImpl implements FundraisingService {
    private final FundraisingRepository fundraisingRepository;
    private final FundraisingMapper fundraisingMapper;

    @Override
    public List<FundraisingDto> getFundraisings(boolean isActive, String category, Pageable pageable) {
        return null;
    }

    @Override
    public FundraisingDto getFundraisingById(long fundraisingId) {
        return null;
    }

    @Override
    public FundraisingDto createFundraising(CreateFundraisingRequestDto requestDto, User user) {
        return null;
    }

    @Override
    public FundraisingDto closeFundraisingById(long fundraisingId, User user) {
        return null;
    }
}
