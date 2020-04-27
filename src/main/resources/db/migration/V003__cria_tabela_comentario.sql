CREATE TABLE comentario (
    id bigint NOT null AUTO_INCREMENT,
    ordem_servico_id bigint NOT NULL,
    descricao text NOT NULL,
    data_envio datetime NOT null,
    
    PRIMARY KEY (id)
);

ALTER TABLE comentario ADD CONSTRAINT fk_comentario_ordem_servico
FOREIGN KEY (ordem_servico_id) REFERENCES Ordem_servico(id);