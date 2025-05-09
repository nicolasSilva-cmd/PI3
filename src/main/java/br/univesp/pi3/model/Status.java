package br.univesp.pi3.model;

import lombok.Getter;

@Getter
public enum Status {

    AGENDADO("agendado"),
    AGUARDANDO("aguardando");

    private String status;

    Status(String status) {this.status = status;}
}
