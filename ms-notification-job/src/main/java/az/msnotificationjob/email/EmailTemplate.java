package az.msnotificationjob.email;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EmailTemplate {
    APPLICATION_CONFIRMATION("application-confirmation.html");

    private final String template;
}
