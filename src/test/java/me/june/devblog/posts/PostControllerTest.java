package me.june.devblog.posts;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PostControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void list() throws Exception {
        ResultActions result = this.mockMvc.perform(get("/posts")
                .param("page","1"));

        result.andDo(print());
        result.andExpect(status().isOk());
    }

    @Test
    public void create() throws Exception {
        PostDto.Create createDto = PostDto.Create.builder()
                .title("제목")
                .contents("내용").build();
        String jsonData = objectMapper.writeValueAsString(createDto);
        System.out.println(jsonData);

        ResultActions result = this.mockMvc.perform(post("/posts").contentType(MediaType.APPLICATION_JSON)
                .content(jsonData));

        result.andDo(print());
        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.title").value(createDto.getTitle()));
        result.andExpect(jsonPath("$.contents").value(createDto.getContents()));
    }
}