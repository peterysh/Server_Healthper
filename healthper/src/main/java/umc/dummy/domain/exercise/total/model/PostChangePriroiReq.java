package umc.dummy.domain.exercise.total.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostChangePriroiReq {
    private Long from;
    private Long to;
}
