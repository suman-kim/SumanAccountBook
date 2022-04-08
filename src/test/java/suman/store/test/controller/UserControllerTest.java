package suman.store.test.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import suman.store.domain.user.UserDto;
import suman.store.domain.user.UserRole;
import suman.store.service.user.UserService;

import static org.assertj.core.api.BDDAssumptions.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void 유저전체조회() throws Exception {

        mvc.perform(get("/api/user/list"))
                .andExpect(status().isOk());
    }

    @Test
    public void 유저한명조회() throws Exception {
        mvc.perform(get("/api/user/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void 유저삭제() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                    .delete("/api/user/1"))
                    .andExpect(status().isOk());
    }

    @Test
    public void 유저수정() throws Exception {

        UserDto userDto = UserDto.builder()
                             .userId("test")
                             .userRole(UserRole.valueOf("ADMIN"))
                             .email("test@naver.com")
                             .phone("01022340555")
                             .name("testName")
                             .build();

        //when & then
        mvc.perform(MockMvcRequestBuilders.put("/api/user/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDto)))
                        .andExpect(status().isOk())
                        .andDo(print());
    }
}
