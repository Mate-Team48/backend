package teamproject.project.dto.fundraising;

import lombok.Data;
import teamproject.project.model.Fundraising;
import teamproject.project.model.User;

@Data
public class CreateFundraisingRequestDto {
    private User user;
    private String name;
    private Fundraising.Category sphere;
    private String fundraisingInfo;
    private String paymentInfo;
    private String pictureUrl;
}
