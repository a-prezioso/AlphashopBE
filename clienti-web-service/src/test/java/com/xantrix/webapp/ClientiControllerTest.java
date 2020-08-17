//package com.xantrix.webapp;
//
// 
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import java.io.IOException;
//
//import org.json.JSONException;
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
//import org.junit.jupiter.api.Order;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestMethodOrder;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.TestPropertySource;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//import com.xantrix.webapp.entities.Cards;
//import com.xantrix.webapp.entities.Clienti;
//import com.xantrix.webapp.repository.ClientiRepository;
//
//@SpringBootTest
//@TestPropertySource(properties = {"profilo = std", "seq = 1"}) 
//@ContextConfiguration(classes = Application.class)
//@TestMethodOrder(OrderAnnotation.class)
//public class ClientiControllerTest 
//{
//	
//	private MockMvc mockMvc;
// 
//	
//	@Autowired
//	private WebApplicationContext wac;
//	
//	@Autowired
//	private ClientiRepository clientiRepository;
//	
//	@BeforeEach
//	public void setup() throws JSONException, IOException
//	{
//		this.mockMvc = MockMvcBuilders
//				.webAppContextSetup(wac)
//				.build();
//		
//		Clienti cliente = new Clienti();
//		cliente.setCodfidelity("65000000");
//		cliente.setNome("Nicola");
//		cliente.setCognome("La Rocca");
//		cliente.setComune("Sassari");
//		cliente.setStato("1");
//		
//		Cards card = new Cards();
//		card.setCodfidelity("65000000");
//		card.setBollini(500);
//		
//		cliente.setCards(card);
//		
//		clientiRepository.save(cliente);
//		
//	}
//	
//	private String ApiBaseUrl = "/api/clienti";
//	
//	String JsonData = 
//			"{\n" + 
//			"    \"codfidelity\": \"65000000\",\n" + 
//			"    \"nominativo\": \"Nicola La Rocca\",\n" + 
//			"    \"indirizzo\": null,\n" + 
//			"    \"comune\": \"Sassari\",\n" + 
//			"    \"cap\": null,\n" + 
//			"    \"telefono\": null,\n" + 
//			"    \"mail\": null,\n" + 
//			"    \"stato\": \"1\",\n" + 
//			"    \"bollini\": 500,\n" + 
//			"    \"ultimaSpesa\": null\n" + 
//			"}";
//	
//	@Test
//	@Order(1)
//	public void testListCliByCode() throws Exception
//	{
//		mockMvc.perform(MockMvcRequestBuilders.get(ApiBaseUrl + "/cerca/codice/65000000")
//				.accept(MediaType.APPLICATION_JSON))
//				.andExpect(status().isOk())
//				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//				.andExpect(content().json(JsonData)) 
//				.andReturn();
//	}
//	
//	@Test
//	@Order(2)
//	public void testModMonteBollAum() throws Exception
//	{
//		mockMvc.perform(MockMvcRequestBuilders.put(ApiBaseUrl + "/cards/modifica/65000000/500")
//				.accept(MediaType.APPLICATION_JSON))
//				.andExpect(status().isOk())
//				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//				.andReturn();
//		
//		Cards card = clientiRepository.findByCodfidelity("65000000").getCards();
//		
//		assertThat(card.getBollini())
//		.isEqualTo(1000);
//	}
//	
//	@Test
//	@Order(3)
//	public void testModMonteBollDim() throws Exception
//	{
//		mockMvc.perform(MockMvcRequestBuilders.put(ApiBaseUrl + "/cards/modifica/65000000/-500")
//				.accept(MediaType.APPLICATION_JSON))
//				.andExpect(status().isOk())
//				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//				.andReturn();
//		
//		Cards card = clientiRepository.findByCodfidelity("65000000").getCards();
//		
//		assertThat(card.getBollini())
//		.isEqualTo(0);
//	}
//	
//	/*
//	@AfterEach
//	public void DelCliente()
//	{
//		clientiRepository.delete(clientiRepository.findByCodfidelity("65000000"));
//	}
//	*/
//	
//	
//	
//}
