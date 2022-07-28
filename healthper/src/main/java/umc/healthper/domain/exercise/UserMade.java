package umc.healthper.domain.exercise;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserMade {
    private Long user_made_id;
    private Long user_id;
    private String name;
    private int section_id;
}
