package by.bsuir.remote.client.model;

public class TodoDto {

  private Long id;
  private String header;
  private String description;

  public TodoDto() {
  }

  public TodoDto(Long id, String header, String description) {
    this.id = id;
    this.header = header;
    this.description = description;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getHeader() {
    return header;
  }

  public void setHeader(String header) {
    this.header = header;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
