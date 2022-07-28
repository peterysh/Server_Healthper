package umc.dummy.domain.routine;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Routine {
    private Long id;
    private Long userId;
    private String name;
    private String sections;
    private int priori;

}
