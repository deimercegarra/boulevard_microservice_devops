package com.pragma.boulevard_microservice_devops.domain.model;


public class CommonResponseModel<E>{

    private String message;
    private String code;
    private E dto;
    private Long id;
    private Boolean status;

    public CommonResponseModel(Boolean status, String code, String message, E dto, Long id) {
        this.message = message;
        this.dto = dto;
        this.id = id;
        this.status = status;
        this.code = code;
    }

    public CommonResponseModel(Boolean status, String code, String message, E dto) {
        this.message = message;
        this.dto = dto;
        this.status = status;
        this.code = code;
    }

    public CommonResponseModel(String code, String message, Boolean status) {
        this.message = message;
        this.status = status;
        this.code = code;
    }

    public CommonResponseModel() {
    }

    @Override
    public String toString() {
        return "{" +
                "message='" + message + '\'' +
                ", code='" + code + '\'' +
                ", dto=" + dto +
                ", id=" + id +
                ", status=" + status +
                '}';
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public E getDto() {
        return dto;
    }

    public void setDto(E dto) {
        this.dto = dto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
