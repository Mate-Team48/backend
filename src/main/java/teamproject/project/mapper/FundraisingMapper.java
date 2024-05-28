package teamproject.project.mapper;

import org.mapstruct.Mapper;
import teamproject.project.config.MapperConfig;
import teamproject.project.dto.fundraising.CreateFundraisingRequestDto;
import teamproject.project.dto.fundraising.FundraisingDto;
import teamproject.project.model.Fundraising;

@Mapper(config = MapperConfig.class)
public interface FundraisingMapper {
    FundraisingDto fundraisingToDto(Fundraising fundraising);

    Fundraising createFundraisingRequestDtoToFundraising(CreateFundraisingRequestDto requestDto);
}
