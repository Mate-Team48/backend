package teamproject.project.mapper;

import org.mapstruct.*;
import teamproject.project.config.MapperConfig;
import teamproject.project.dto.fundraising.*;
import teamproject.project.model.*;

@Mapper(config = MapperConfig.class)
public interface FundraisingMapper {
    FundraisingDto fundraisingToDto(Fundraising fundraising);
}
