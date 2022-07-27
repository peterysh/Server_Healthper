package umc.healthper.domain.exercise.total.userlikeRepository;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import umc.healthper.domain.exercise.total.model.GetUserLikeRes;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class MemoryUserLikeRepositoryTest {

    MemoryUserLikeRepository repository;
    @BeforeEach
    void before(){
        repository = new MemoryUserLikeRepository();
        repository.doLike(1l,1l,0);
        repository.doLike(2l,1l,0);
        repository.doLike(3l,1l,0);
        repository.doLike(1l,2l,0);
        repository.doLike(2l,2l,0);
    }

    @Test
    public void unlike(){
        repository.undoLike(2l);
        listing();
    }

    @Test
    void listing(){
        List<GetUserLikeRes> likeList = repository.getLikeList(0, 1l);
        assertThat(likeList.size()).isEqualTo(3);
        for (GetUserLikeRes res : likeList) {
            log.info("{}",res.toString());
        }
    }
    @Test
    void fixPriori(){
        repository.changePriory(1l,4l);
        listing();
    }
}