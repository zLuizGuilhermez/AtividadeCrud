Introdução

Integrantes:

- Luiz Guilherme Oliveira da Mota 
- Mateus Bolivar Vieira Duarte 
- Yago Mello Mesquita

Criar o projeto

Utilizamos o site maven initialzr, que permite gerar um projeto maven com as dependencias configuradas.

⚙️ Configurações utilizadas:

    Project: Maven

    Language: Java

    Spring Boot: 3.x

    Package Name: com.AtividadeCrud.example.demo

    Packaging: Jar

    Java: 21

📦 Dependências adicionadas:

    spring-boot-starter-web – para criação da API REST.

    spring-boot-starter-data-jpa – para integração com banco de dados usando JPA.

    spring-boot-starter-test – para criação de testes unitários e de integração.

    mysql-connector-java (v8.0.33) – para conexão com banco de dados MySQL.

🧱 2. Criando a API com o Gerador de Código

 Estamos usando o spring boot então precisamos criar as pastas dar arquiterura manualmente. 

Explicação do Controller e das Anotações

A seguir, mostramos um exemplo de um controller no sistema.

    package com.AtividadeCrud.example.demo.controller;

    import com.AtividadeCrud.example.demo.entity.CidadeEntity;
    import com.AtividadeCrud.example.demo.service.CidadeService;
    import com.AtividadeCrud.example.demo.dto.CidadeDto;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.web.bind.annotation.*;

    @RestController
    @RequestMapping("/cidade")
    public class CidadeController {

    @Autowired
    CidadeService cidadeService;

    @PostMapping("/criarcidade/{sigla}")
    public void setCidade(@RequestBody CidadeDto cidadeDto, @PathVariable String sigla){
        cidadeService.criarCidade(cidadeDto.getNome(), sigla);
        }

    @PutMapping("/alterarCidade")
    public String alteraInstanciaCidade(@RequestBody CidadeDto cidadeDto){
        cidadeService.alterarCidade(cidadeDto.getNomeAntigo(), cidadeDto.getNomeAtual());
        return "Cidade Alterada! " + cidadeDto.getNomeAtual();
        }

    @GetMapping("/getCidade/{nome}")
    public CidadeEntity getCidade(@PathVariable String nome){
        return cidadeService.buscarCidadePorNome(nome);
        }

    @DeleteMapping("/deletarCidade/{nome}")
    public String removerCidade(@PathVariable String nome){
         cidadeService.removerCidade(nome);
         return "Removido com sucesso";
        }
    }

 Explicações importantes:

    @RestController: indica que esta classe é um controller permitindo a serialização e a deserialização

    @RequestMapping("/cidade"): define o caminho base dos endpoints dessa classe. Todos os métodos responderão a URLs que comecem com /cidade.

    @Autowired: injeta automaticamente a instância da classe CidadeService no controller, facilitando a separação de responsabilidades.

    @PostMapping, @PutMapping, @GetMapping, @DeleteMapping: indicam os métodos HTTP correspondentes (POST, PUT, GET e DELETE), com seus respectivos caminhos e ações.

    @RequestBody: converte automaticamente os dados recebidos em JSON no corpo da requisição para um objeto Java (neste caso, um CidadeDto).

    @PathVariable: captura o valor da URL como parâmetro do método.

Essa classe representa a tabela cidade no banco de dados. Usamos as anotações do JPA pra mapear tudo certo.

    package com.AtividadeCrud.example.demo.entity;
    
    import com.fasterxml.jackson.annotation.JsonIgnore;
    import jakarta.persistence.*;
    import java.util.List;

    @Entity
    @Table(name = "cidade")
    public class CidadeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    @ManyToOne
    @JoinColumn(name = "uf_id", nullable = false)
    @JsonIgnore
    private UfEntity uf;

    @OneToMany(mappedBy = "cidade", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<EstudanteEntity> estudantes;

    // Getters e Setters...

    }

Explicações importantes:

    @Entity: diz que essa classe é uma entidade do banco, ou seja, vira uma tabela.

    @Table(name = "cidade"): dá o nome da tabela no banco.

    @Id e @GeneratedValue: esse campo é a chave primária (id) e vai ser gerado automaticamente.

    @ManyToOne e @JoinColumn: aqui a cidade está ligada a uma UF (tipo SP, RJ...). Cada cidade tem uma UF.

    @OneToMany: uma cidade pode ter vários estudantes. A anotação faz esse vínculo. O mappedBy mostra que quem cuida dessa relação é a outra classe (EstudanteEntity).

    @JsonIgnore: essa anotação serve pra ignorar esses campos quando a gente transforma em JSON (tipo quando responde na API). Ajuda a evitar erros e loops infinitos na hora de serializar.

🔌 Conexão com o Banco de Dados - application.properties

Esse arquivo é onde configura como a aplicação Spring vai se conectar com o banco.

spring.datasource.url=jdbc:mysql://localhost:3306/atividade

    Essa é a URL do banco.

    localhost: significa que o banco está rodando na minha máquina.

    3306: é a porta padrão do MySQL.

    atividade: é o nome do banco de dados que eu criei


spring.jpa.hibernate.ddl-auto=update

    Vai gerar apenas uma vez e vai ficar atualizando o banco.

spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

    driver do jdbc

spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect

    Define o dialeto do sql.

🛠️ Lógica na Service

Essa classe é onde fica a lógica e as regras de négocio . Ela faz a ponte entre o Controller (onde chegam as requisições) e o Repository (que fala direto com o banco).

Injeção de Dependências

    @Autowired
    CidadeRepository cidadeRepository;

    @Autowired
    UfService ufService;

O cidadeRepository serve pra salvar, buscar, editar e deletar cidades do banco.

O ufService vai ser usada para buscar a sigla da uf que precisamos

🏙️ Criar Cidade

    public void criarCidade(String nome, String uf){
    UfEntity ufEntityParaId = ufService.buscarUfPorSigla(uf);
    CidadeEntity cidadeEntity = new CidadeEntity();
    cidadeEntity.setNome(nome);
    cidadeEntity.setUf(ufEntityParaId);
    cidadeRepository.save(cidadeEntity);
    }

Cria uma nova cidade usando o nome e a sigla do estado (UF).

Primeiro busca o estado com a sigla, depois cria a cidade e salva no banco.

✏️ Alterar Cidade

    public void alterarCidade(String nomeAtual, String nomeAtualizado) {
    Optional<CidadeEntity> cidadeEditada = cidadeRepository.findFirstByNome(nomeAtual);

    if (cidadeEditada.isPresent()) {
        CidadeEntity cidade = cidadeEditada.get();
        cidade.setNome(nomeAtualizado);
        cidadeRepository.save(cidade);
    } else {
        throw new EntityNotFoundException("Cidade com nome '" + nomeAtual + "' não encontrada.");
        }
    }

Procura a cidade pelo nome atual.

Se achar, altera o nome e salva de novo no banco.

Se não achar, lança um erro dizendo que não encontrou.

❌ Remover Cidade

    public void removerCidade(String nome){
    Optional<CidadeEntity> cidadeDel = cidadeRepository.findFirstByNome(nome);

    if(cidadeDel.isPresent()){
        CidadeEntity cidadeEntity = cidadeDel.get();
        cidadeRepository.delete(cidadeEntity);
        }
    }

Procura a cidade pelo nome e, se existir, deleta do banco.

🔍 Buscar Cidade por Nome

    public CidadeEntity buscarCidadePorNome(String nome) {
    return cidadeRepository.findFirstByNome(nome)
    .orElseThrow(() -> new RuntimeException("UF não encontrada"));
    }

Retorna uma cidade pelo nome.

Se não encontrar, lança um erro.

🗂️ Repositório da Cidade (CidadeRepository)

O repositório é responsável por falar criar as transações com o banco de dados. Ele estende a interface JpaRepository, que já entrega os metodos para crud

    @Repository
    public interface CidadeRepository extends JpaRepository<CidadeEntity, Long> {

    Optional<CidadeEntity> findFirstByNome(String nome);
    }

Explicações importantes:

    @Repository: Diz que essa interface é um repositório do Spring, que será usado pra acessar os dados no banco.

    extends JpaRepository<CidadeEntity, Long>:

O CidadeEntity é a entidade (tabela) que esse repositório vai manipular.

O Long é o tipo do ID da cidade (se fosse Integer, colocaria Integer ali).

    findFirstByNome(String nome):

Esse é um método customizado. Ele busca a primeira cidade com aquele nome.

Como retorna Optional<CidadeEntity>, ajuda a evitar NullPointerException.