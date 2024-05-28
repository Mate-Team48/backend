package teamproject.project.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import teamproject.project.dto.fundraising.CreateFundraisingRequestDto;
import teamproject.project.dto.fundraising.FundraisingDto;
import teamproject.project.exception.EntityNotFoundException;
import teamproject.project.exception.UserNotFundraisingOwnerException;
import teamproject.project.mapper.FundraisingMapper;
import teamproject.project.model.Fundraising;
import teamproject.project.model.User;
import teamproject.project.repository.FundraisingRepository;
import teamproject.project.service.FundraisingService;

@Service
@RequiredArgsConstructor
public class FundraisingServiceImpl implements FundraisingService {
    private final FundraisingRepository fundraisingRepository;
    private final FundraisingMapper fundraisingMapper;

    @Override
    public List<FundraisingDto> getFundraisings(
            boolean isActive,
            String category,
            Pageable pageable
    ) {
        Fundraising.Category fundraisingCategory =
                Fundraising.Category.valueOf(category.toUpperCase());
        List<Fundraising> fundraisings = fundraisingRepository
                .findAllByIsActiveAndCategory(isActive, fundraisingCategory, pageable);
        return fundraisings.stream()
                .map(fundraisingMapper::fundraisingToDto)
                .toList();
    }

    @Override
    public FundraisingDto getFundraisingById(long fundraisingId) {
        Fundraising fundraising = findFundraisingById(fundraisingId);
        return fundraisingMapper.fundraisingToDto(fundraising);
    }

    @Override
    public FundraisingDto createFundraising(CreateFundraisingRequestDto requestDto, User user) {
        Fundraising newFundraising =
                fundraisingMapper.createFundraisingRequestDtoToFundraising(requestDto);
        newFundraising.setUser(user);
        Fundraising savedNewFundraising = fundraisingRepository.save(newFundraising);
        return fundraisingMapper.fundraisingToDto(savedNewFundraising);
    }

    @Override
    public FundraisingDto closeFundraisingById(long fundraisingId, User user) {
        Fundraising fundraising = findFundraisingById(fundraisingId);
        checkIfUserIsFundraisingOwner(user, fundraising);
        fundraising.setFinished(true);
        Fundraising savedFinishedFundraising = fundraisingRepository.save(fundraising);
        return fundraisingMapper.fundraisingToDto(savedFinishedFundraising);
    }

    private Fundraising findFundraisingById(long fundraisingId) {
        return fundraisingRepository.findById(fundraisingId).orElseThrow(
                () -> new EntityNotFoundException(
                        "Fundrising with id " + fundraisingId + " was not found!"));
    }

    private void checkIfUserIsFundraisingOwner(User user, Fundraising fundraising) {
        if (!user.getId().equals(fundraising.getUser().getId())) {
            throw new UserNotFundraisingOwnerException("User with id " + user.getId()
                    + "is not owner of fundraising with id " + fundraising.getId());
        }
    }
}
