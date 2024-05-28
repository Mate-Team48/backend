package teamproject.project.dto.fundraising;

import lombok.*;
import teamproject.project.model.*;

@Data
public class CreateFundraisingRequestDto {
    private User user;
    private String name;
    private Fundraising.Category sphere;
    private String fundraisingInfo;
    private String paymentInfo;
    private String pictureUrl;
}
