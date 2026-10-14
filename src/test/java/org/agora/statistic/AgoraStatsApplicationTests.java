package org.agora.statistic;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.agora.statistic.service.LikeEventListener;
import org.agora.statistic.service.PostEventListener;

@SpringBootTest
class AgoraStatsApplicationTests {

	@MockitoBean
	private LikeEventListener likeEventListener;

	@MockitoBean
	private PostEventListener postEventListener;

	@Test
	void contextLoads() {
	}

}
