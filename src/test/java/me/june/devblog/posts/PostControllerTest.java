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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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

    @Test
    public void update() throws Exception {
        PostDto.Create createDto = PostDto.Create.builder()
                .title("제목")
                .contents("내용").build();
        String jsonData = objectMapper.writeValueAsString(createDto);
        ResultActions result = this.mockMvc.perform(post("/posts").contentType(MediaType.APPLICATION_JSON)
                .content(jsonData));

        result.andDo(print());
        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.title").value(createDto.getTitle()));
        result.andExpect(jsonPath("$.contents").value(createDto.getContents()));
        MvcResult mvcResult = result.andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();

        Post post = objectMapper.readValue(contentAsString, Post.class);
        PostDto.Update updateDto = PostDto.Update.builder()
                .seq(post.getId())
                .contents("내용 수정")
                .title("제목 수정")
                .build();

        result = this.mockMvc.perform(put("/posts/"+post.getId()).contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(updateDto)));

        result.andDo(print());
        result.andExpect(status().isNoContent());
    }
}