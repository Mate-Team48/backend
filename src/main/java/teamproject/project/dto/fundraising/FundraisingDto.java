package teamproject.project.dto.fundraising;

import jakarta.persistence.*;
import lombok.*;
import teamproject.project.model.*;

@Data
public class FundraisingDto {
    private long id;
    private User user;
    private String name;
    private Fundraising.Category sphere;
    private String fundraisingInfo;
    private String paymentInfo;
    private String pictureUrl;
    private boolean isFinished = false;
}
