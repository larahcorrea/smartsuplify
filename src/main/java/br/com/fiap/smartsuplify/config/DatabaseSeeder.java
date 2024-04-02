package br.com.fiap.smartsuplify.config;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.fiap.smartsuplify.model.Cotacao;
import br.com.fiap.smartsuplify.model.Estoque;
import br.com.fiap.smartsuplify.model.Solicitacao;
import br.com.fiap.smartsuplify.model.adquirivel.Produto;
import br.com.fiap.smartsuplify.model.adquirivel.Servico;
import br.com.fiap.smartsuplify.model.pessoa.PessoaFisica;
import br.com.fiap.smartsuplify.model.pessoa.PessoaJuridica;
import br.com.fiap.smartsuplify.repository.CotacaoRepository;
import br.com.fiap.smartsuplify.repository.EstoqueRepository;
import br.com.fiap.smartsuplify.repository.PessoaFisicaRepository;
import br.com.fiap.smartsuplify.repository.PessoaJuridicaRepository;
import br.com.fiap.smartsuplify.repository.ProdutoRepository;
import br.com.fiap.smartsuplify.repository.ServicoRepository;
import br.com.fiap.smartsuplify.repository.SolicitacaoRepository;

@Configuration
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired
    PessoaFisicaRepository pessoaFisicaRepository;

    @Autowired
    PessoaJuridicaRepository pessoaJuridicaRepository;

    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    ServicoRepository servicoRepository;

    @Autowired
    EstoqueRepository estoqueRepository;

    @Autowired
    SolicitacaoRepository solicitacaoRepository;

    @Autowired
    CotacaoRepository cotacaoRepository;

    private PessoaJuridica empresa1 = new PessoaJuridica(null, "Flor de Mim", "95122220000102");
    private PessoaJuridica empresa2 = new PessoaJuridica(null, "Meg Meg", "71133376000112");

    private PessoaFisica pessoa = new PessoaFisica(null, "Ana", "69947134075", "ana@email.com.br", "senha123");

    private Produto produto1 = new Produto(null, "Caneta", "20cm");
    private Produto produto2 = new Produto(null, "gasolina", "1000L");
    private Produto produto3 = new Produto(null, "copos", "20cm");

    private Servico servico1 = new Servico(null, "Assinatura Alura", null);
    private Servico servico2 = new Servico(null, "Workshop", "3 horas");

    private Estoque estoque = new Estoque(null, 200, 1000, 750, produto1, empresa2);
    

    private Solicitacao solicitacao1 = new Solicitacao(null, 250.0, produto1);
    private Solicitacao solicitacao2 = new Solicitacao(null, 1000.0, servico1);
    private Solicitacao solicitacao3 = new Solicitacao(null, 3.0, servico2);

    
    @Override
    public void run(String... args) throws Exception {

        pessoaFisicaRepository.saveAll(
            List.of(pessoa)
        );

        pessoaJuridicaRepository.saveAll(
            List.of(empresa1, empresa2)
        );

        produtoRepository.saveAll(
            List.of(produto1, produto2, produto3)
        );

        servicoRepository.saveAll(
            List.of(servico1, servico2)
        );

        estoqueRepository.saveAll(
            List.of(estoque)
        );

        solicitacaoRepository.saveAll(
            List.of(solicitacao1, solicitacao2, solicitacao3)
        );

        
        cotacaoRepository.saveAll(
            List.of(
                new Cotacao()
                    .withData(LocalDate.now().minusDays(2L))
                    .withStatus("Aguardando retorno")
                    .withFuncionario(pessoa)
                    .addSolicitacao(solicitacao1)
                    .addSolicitacao(solicitacao2)
                    .addSolicitacao(solicitacao3)

            )
        );

        
        
        
    }

    
}
