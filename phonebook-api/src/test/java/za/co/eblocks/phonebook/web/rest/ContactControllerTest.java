package za.co.eblocks.phonebook.web.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import za.co.eblocks.phonebook.persistance.entity.Contact;

import java.util.Date;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@SpringBootTest
@ActiveProfiles({"test"})
class ContactControllerTest {
    private static final long ID = 1;

    private MockMvc mockMvc;

    @Autowired
    private ContactController controller;

    @BeforeEach
    @Sql({"/insertContacts.sql"})
    @Sql(scripts= "/deleteContacts.sql",executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void setUp() {
        this.mockMvc = standaloneSetup(controller).build();
    }

    @Test
    @Sql({"/insertContacts.sql"})
    void getAllContacts() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
        .get("/contacts/v1")
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[*]").exists())
        .andExpect(MockMvcResultMatchers.jsonPath("$[*].id").isNotEmpty());
    }

    @Test
    @Sql({"/insertContacts.sql"})
    @Sql(scripts= "/deleteContacts.sql",executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void getContactById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/contacts/v1/{id}", ID)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }

    @Test
//    @Sql(scripts= "/deleteContacts.sql",executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void createContact() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/contacts/v1")
                .content(asJsonString(new Contact(null,"Mike","mike@test.com","0837531563","Other", new Date())))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql({"/insertContacts.sql"})
    void updateContact() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .put("/contacts/v1/{id}",4)
                .content(asJsonString(new Contact(4L, "firstName1","email2@mail.com","0792222643","Mobile", new Date())))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Sql({"/insertContacts.sql"})
    @Sql(scripts= "/deleteContacts.sql",executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void deleteContact() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/contacts/v1/{id}", ID))
                .andExpect(status().isOk());
    }
}
