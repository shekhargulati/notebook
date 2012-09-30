package com.openshift.notebook.web;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.server.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.server.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.server.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.server.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.status;

import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.server.MockMvc;
import org.springframework.test.web.server.setup.MockMvcBuilders;

import com.openshift.notebook.core.domain.Notebook;
import com.openshift.notebook.core.domain.NotebookBuilder;
import com.openshift.notebook.core.service.NotebookService;
import com.openshift.notebook.web.controllers.NotebookController;

@RunWith(MockitoJUnitRunner.class)
public class NotebookControllerUnitTest {

	@Mock
	NotebookService notebookService;

	@InjectMocks
	private NotebookController notebookController = new NotebookController();

	@Test
	public void shouldCreateNotebook() throws Exception {
		String[] tags = { "test" };
		Notebook notebook = NotebookBuilder.notebook()
				.withAuthor("test_author").withDescription("test notebook")
				.withName("test").withTags(tags).build();
		String id = UUID.randomUUID().toString();
		notebook.setId(id);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");
		MockMvc mvc = MockMvcBuilders.standaloneSetup(notebookController)
				.build();

		String json = notebook.toJson();

		when(notebookService.createNewNotebook(any(Notebook.class)))
				.thenReturn(notebook);
		mvc.perform(post("/notebooks").headers(headers).body(json.getBytes()))
				.andExpect(status().isCreated());

		verify(notebookService).createNewNotebook(any(Notebook.class));

	}

	public void testShow() throws Exception {
		String[] tags = { "test" };
		Notebook notebook = NotebookBuilder.notebook()
				.withAuthor("test_author").withDescription("test notebook")
				.withName("test").withTags(tags).build();
		String id = UUID.randomUUID().toString();
		notebook.setId(id);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");
		MockMvc mvc = MockMvcBuilders.standaloneSetup(notebookController)
				.build();

		String json = notebook.toJson();

		when(notebookService.findNotebook(id)).thenReturn(notebook);

		mvc.perform(get("/notebooks/{id}", id).headers(headers))
				.andExpect(status().isOk())
				.andExpect(content().type("application/json; charset=utf-8"))
				.andExpect(content().string(json));
		verify(notebookService).findNotebook(id);
	}

	public void update() throws Exception {
		String[] tags = { "test" };
		Notebook notebook = NotebookBuilder.notebook()
				.withAuthor("test_author").withDescription("test notebook")
				.withName("test").withTags(tags).build();
		String id = UUID.randomUUID().toString();
		notebook.setId(id);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");
		MockMvc mvc = MockMvcBuilders.standaloneSetup(notebookController)
				.build();

		String json = notebook.toJson();

		notebook.setDescription("test notebook updated");

		String updatedNotebookJson = notebook.toJson();

		when(notebookService.updateNotebook(any(Notebook.class))).thenReturn(
				notebook);
		mvc.perform(
				put("/notebooks/{id}", id).headers(headers).body(
						updatedNotebookJson.getBytes()))
				.andExpect(status().isOk())
				.andExpect(content().type("application/json"))
				.andExpect(content().string(updatedNotebookJson));

		verify(notebookService).updateNotebook(any(Notebook.class));
	}

	public void deleteNotebook() throws Exception {
		String[] tags = { "test" };
		Notebook notebook = NotebookBuilder.notebook()
				.withAuthor("test_author").withDescription("test notebook")
				.withName("test").withTags(tags).build();
		String id = UUID.randomUUID().toString();
		notebook.setId(id);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");
		MockMvc mvc = MockMvcBuilders.standaloneSetup(notebookController)
				.build();

		String json = notebook.toJson();
		doNothing().when(notebookService).deleteNotebook(any(Notebook.class));
		mvc.perform(delete("/notebooks/{id}", id).headers(headers)).andExpect(
				status().isOk());
		verify(notebookService).deleteNotebook(any(Notebook.class));

		when(notebookService.findNotebook(id)).thenReturn(null);
	}
}
