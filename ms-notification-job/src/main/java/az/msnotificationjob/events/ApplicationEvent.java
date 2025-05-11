package az.msnotificationjob.events;

import az.msnotificationjob.enums.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationEvent {
    private Long applicantId;
    private String applicantName;
    private String applicantMail;
    private StatusEnum status;
}
