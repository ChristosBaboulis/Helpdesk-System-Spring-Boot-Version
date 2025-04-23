package com.example.demo.resource;

import com.example.demo.NoSecurityTestConfig;
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
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

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
        Assertions.assertEquals("TECH001", technicians.get(0).techCode);
    }
}
