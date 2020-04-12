package by.bsuir.remote.client.controller;

import by.bsuir.jee.MessageService;
import javax.naming.NamingException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message")
public class MessageController {

  private final MessageService messageService;

  public MessageController(MessageService messageService) {
    this.messageService = messageService;
  }

  @PostMapping
  public ResponseEntity<String> create(@RequestBody String text) {
    messageService.postText(text);
    return new ResponseEntity<>(text, new HttpHeaders(), HttpStatus.OK);
  }
}
