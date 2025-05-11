package az.msjobsearch.dao.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserSearchResponse {
    private String username;
    private String name;
    private String surname;
}
