package com.example.demo.resource;

import com.example.demo.NoSecurityTestConfig;
import com.example.demo.representation.AddressRepresentation;
import com.example.demo.representation.TechnicianRepresentation;
import com.example.demo.service.TechnicianService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Sql("/import.sql")
@AutoConfigureMockMvc
@Import(NoSecurityTestConfig.class)
public class TechnicianResourceTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TechnicianResource technicianResource;

    @Autowired
    private TechnicianService technicianService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetTechnicianTest() throws Exception {
        MvcResult result = mockMvc.perform(get("/technicians/4001"))
                .andExpect(status().isOk())
                .andReturn();

        String json = result.getResponse().getContentAsString();
        TechnicianRepresentation t = objectMapper.readValue(json, TechnicianRepresentation.class);

        Assertions.assertEquals("TECH001", t.techCode);
    }

    @Test
    public void testGetTechnicianWithNullId() throws Exception{
        MvcResult result = mockMvc.perform(get("/technicians/99999"))
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    public void testGetTechnicians() throws Exception{
        MvcResult result = mockMvc.perform(get("/technicians"))
                .andExpect(status().isOk())
                .andReturn();

        String json = result.getResponse().getContentAsString();
        List<TechnicianRepresentation> technicians = objectMapper.readValue(json,
                new TypeReference<List<TechnicianRepresentation>>() {
        });

        Assertions.assertFalse(technicians.isEmpty());
        Assertions.assertEquals("TECH001", technicians.getFirst().techCode);
    }

    @Test
    public void testGetTechniciansByTechCode() throws Exception{
        MvcResult result = mockMvc.perform(get("/technicians/tech_code/TECH001"))
                .andExpect(status().isOk())
                .andReturn();
        String json = result.getResponse().getContentAsString();
        List<TechnicianRepresentation> technicians = objectMapper.readValue(json, new TypeReference<List<TechnicianRepresentation>>() {
        });

        Assertions.assertFalse(technicians.isEmpty());
        Assertions.assertEquals("TECH001", technicians.getFirst().techCode);
    }

    @Test
    public void testGetTechniciansByUsername() throws Exception{
        MvcResult result = mockMvc.perform(get("/technicians/username/tech1"))
                .andExpect(status().isOk())
                .andReturn();
        String json = result.getResponse().getContentAsString();
        List<TechnicianRepresentation> technicians = objectMapper.readValue(json, new TypeReference<List<TechnicianRepresentation>>() {
        });
        Assertions.assertFalse(technicians.isEmpty());
        Assertions.assertEquals("tech1", technicians.getFirst().username);
    }

    @Test
    public void testGetTechniciansByFirstName() throws Exception{
        MvcResult result = mockMvc.perform(get("/technicians/first_name/Christos"))
                .andExpect(status().isOk())
                .andReturn();
        String json = result.getResponse().getContentAsString();
        List<TechnicianRepresentation> technicians = objectMapper.readValue(json, new TypeReference<List<TechnicianRepresentation>>() {
        });
        Assertions.assertFalse(technicians.isEmpty());
        Assertions.assertEquals("Christos", technicians.getFirst().firstName);
    }

    @Test
    public void testGetTechniciansByLastName() throws Exception{
        MvcResult result = mockMvc.perform(get("/technicians/last_name/Brown"))
                .andExpect(status().isOk())
                .andReturn();
        String json = result.getResponse().getContentAsString();
        List<TechnicianRepresentation> technicians = objectMapper.readValue(json, new TypeReference<List<TechnicianRepresentation>>() {
        });
        Assertions.assertFalse(technicians.isEmpty());
        Assertions.assertEquals("Brown", technicians.getFirst().lastName);
    }

    @Test
    public void testGetTechniciansByEmail() throws Exception{
        MvcResult result = mockMvc.perform(get("/technicians/email/david.brown@example.com"))
                .andExpect(status().isOk())
                .andReturn();
        String json = result.getResponse().getContentAsString();
        List<TechnicianRepresentation> technicians = objectMapper.readValue(json, new TypeReference<List<TechnicianRepresentation>>() {
        });
        Assertions.assertFalse(technicians.isEmpty());
        Assertions.assertEquals("david.brown@example.com", technicians.getFirst().email);
    }

    @Test
    public void testGetTechniciansByPhone() throws Exception{
        MvcResult result = mockMvc.perform(get("/technicians/phone/3216549870"))
                .andExpect(status().isOk())
                .andReturn();
        String json = result.getResponse().getContentAsString();
        List<TechnicianRepresentation> technicians = objectMapper.readValue(json, new TypeReference<List<TechnicianRepresentation>>() {
        });
        Assertions.assertFalse(technicians.isEmpty());
        Assertions.assertEquals("3216549870", technicians.getFirst().phone);
    }

    @Test
    public void testGetTechniciansByBirthdate() throws Exception{
        MvcResult result = mockMvc.perform(get("/technicians/birthdate/1985-09-15"))
                .andExpect(status().isOk())
                .andReturn();
        String json = result.getResponse().getContentAsString();
        List<TechnicianRepresentation> technicians = objectMapper.readValue(json, new TypeReference<List<TechnicianRepresentation>>() {
        });
        Assertions.assertFalse(technicians.isEmpty());
        LocalDate expectedBirthdate = LocalDate.of(1985, 9, 15);
        Assertions.assertEquals(expectedBirthdate, technicians.getFirst().birthdate);
    }

    @Test
    public void testGetTechniciansByAddress() throws Exception{
        AddressRepresentation address = new AddressRepresentation();
        address.country = "USA";
        address.state = "LA";
        address.city = "San Francisco";
        address.street = "Elm Street";
        address.streetNumber = "10";
        address.zipCode = "94102";

        MvcResult result = mockMvc.perform(get("/technicians/address")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(address)))
                .andExpect(status().isOk())
                .andReturn();

        String json = result.getResponse().getContentAsString();
        List<TechnicianRepresentation> technicians = objectMapper.readValue(json, new TypeReference<List<TechnicianRepresentation>>() {
        });

        Assertions.assertFalse(technicians.isEmpty());
    }
}
