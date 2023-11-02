package com.jubaer.testproject.Book;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.jubaer.testproject.Book.controller.BookController;
import com.jubaer.testproject.Book.model.Book;
import com.jubaer.testproject.Book.repository.BookRepository;


import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class BookControllerTest {

    private MockMvc mockMvc;
    
    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectWriter = objectMapper.writer();

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookController bookController;

    // Book instances
    Book Record_1 = new Book(1L, "Atomic Habits", "How to build better element", 4);
	Book Record_2 = new Book(2L, "C Programming", "How to build better element", 6);
	Book Record_3 = new Book(3L, "Computer Graphis", "How to build better element", 5);

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
    }

    @Test
    public void getAllRecords_success() throws Exception {
        List<Book> records = new ArrayList<>(Arrays.asList(Record_1, Record_2, Record_3));
        Mockito.when(bookRepository.findAll()).thenReturn(records);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/book")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[2].name", is("Computer Graphis")))
                .andExpect(jsonPath("$[1].name", is("C Programming")));
    }
    
    @Test
    public void getBookById_Success() throws Exception{
    	Mockito.when(bookRepository.findById(Record_1.getId())).thenReturn(java.util.Optional.of(Record_1));
    	
    	mockMvc.perform(MockMvcRequestBuilders
                .get("/book/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
//                .andExpect(jsonPath("$[2].name", is("Computer Graphis")))
                .andExpect(jsonPath("$.name", is("Atomic Habits")));
    
    }
    
    
    @Test
    public void createRecord_success() throws Exception{
    	
    	Book record = Book.builder()
    			.id(4L)
    			.name("Java Programming")
    			.summary("The name is string")
    			.rating(7)
    			.build();
    	Mockito.when(bookRepository.save(record)).thenReturn(record);
    	
    	String content = objectWriter.writeValueAsString(record);
    	
    	MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/book")
    			.contentType(MediaType.APPLICATION_JSON)
    			.accept(MediaType.APPLICATION_JSON)
    			.content(content);
    	mockMvc.perform(mockRequest)
    	.andExpect(status().isOk())
        .andExpect(jsonPath("$", notNullValue()))
        .andExpect(jsonPath("$.name", is("Java Programming")));

    }
    
    
    @Test
    public void updateBookRecord_success() throws Exception{
    	Book updatedRecord = Book.builder()
    			.id(1L)
    			.name("Updated Book name")
    			.summary("Updated summary")
    			.rating(1).build();
    	
    	Mockito.when(bookRepository.findById(Record_1.getId())).thenReturn(java.util.Optional.ofNullable(Record_1));
    	Mockito.when(bookRepository.save(updatedRecord)).thenReturn(updatedRecord);
    	
    	String updatedContent = objectWriter.writeValueAsString(updatedRecord);
    	
    	MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/book")
    			.contentType(MediaType.APPLICATION_JSON)
    			.accept(MediaType.APPLICATION_JSON)
    			.content(updatedContent);
    	mockMvc.perform(mockRequest)
    	.andExpect(status().isOk())
        .andExpect(jsonPath("$", notNullValue()))
        .andExpect(jsonPath("$.name", is("Java Programming")));

    	
    	
    }
    
//    create a test for not found exception
    
    @Test
    public void deleteBookById_success() throws Exception{
    	Mockito.when(bookRepository.findById(Record_2.getId())).thenReturn(java.util.Optional.ofNullable(Record_2));

    	
    	mockMvc.perform(MockMvcRequestBuilders
    	.delete("/book/2")
        .contentType(MediaType.APPLICATION_JSON))
    	.andExpect(status().isOk());
    	
    	
    	
    }
    
    
    
    
    
    
}
