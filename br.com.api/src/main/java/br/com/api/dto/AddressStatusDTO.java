package br.com.api.dto;

public class AddressStatusDTO {

    private Long id;

    private Integer status;

    public AddressStatusDTO() { }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}
