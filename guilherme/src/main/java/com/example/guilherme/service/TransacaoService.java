package com.example.guilherme.service;


import com.example.guilherme.model.Cliente;
import com.example.guilherme.model.Empresa;
import com.example.guilherme.model.Transacao;
import com.example.guilherme.repository.ClienteRepository;
import com.example.guilherme.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TransacaoService {
    @Autowired
    private TaxaService taxaService;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private RestTemplate restTemplate;

    private String webhookUrl = "https://webhook.site/42a0c8a7-1000-49d5-a601-79401c6a4c57";

    public void processarTransacao(Transacao transacao) {
        if ("cliente".equalsIgnoreCase(transacao.getTipoDestino())) {
            processarTransacaoCliente(transacao);
        } else if ("empresa".equalsIgnoreCase(transacao.getTipoDestino())) {
            processarTransacaoEmpresa(transacao);
        } else {
            throw new RuntimeException("Tipo de destino inválido.");
        }
    }

    private void processarTransacaoCliente(Transacao transacao) {
        Cliente cliente = clienteRepository.findByCpf(transacao.getCpfDestino());
        if (cliente == null) {
            throw new RuntimeException("Cliente não encontrado.");
        }

        aplicarTaxa(transacao);

        // Envie a notificação por e-mail para o cliente
        String mensagem = "Você realizou uma transação no valor de " + transacao.getValor() + " " + transacao.getTipo();
        notificarClientePorEmail(cliente.getEmail(), "Notificação de Transação", mensagem);

        // Envie a notificação para o Webhook.site
        restTemplate.postForObject(webhookUrl, mensagem, String.class);
    }

    private void processarTransacaoEmpresa(Transacao transacao) {
        Empresa empresa = empresaRepository.findByCnpj(transacao.getCnpjDestino());
        if (empresa == null) {
            throw  new RuntimeException("Empresa não encontrada.");
        }

        aplicarTaxa(transacao);

        if ("depósito".equalsIgnoreCase(transacao.getTipo())) {
            realizarDeposito(empresa, transacao.getValor());
        } else if ("saque".equalsIgnoreCase(transacao.getTipo())) {
            realizarSaque(empresa, transacao.getValor());
        }

        // Enviar notificação por e-mail para a empresa
        String mensagem = "Você recebeu uma transação de " + transacao.getValor() + " " + transacao.getTipo() + " do cliente.";
        notificarEmpresaPorEmail(empresa.getEmail(), "Notificação de Transação", mensagem);

        // Enviar notificação para o Webhook.site
        restTemplate.postForObject(webhookUrl, mensagem, String.class);
    }

    private void aplicarTaxa(Transacao transacao) {
        double taxa = taxaService.calcularTaxa(transacao);
        transacao.setValor(transacao.getValor() - taxa);
    }

    private void notificarClientePorEmail(String destinatario, String assunto, String mensagem) {
        enviarEmail(destinatario, assunto, mensagem);
    }

    private void notificarEmpresaPorEmail(String destinatario, String assunto, String mensagem) {
        enviarEmail(destinatario, assunto, mensagem);
    }

    private void enviarEmail(String destinatario, String assunto, String mensagem) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(destinatario);
        email.setSubject(assunto);
        email.setText(mensagem);
        javaMailSender.send(email);
    }

    private void realizarDeposito(Empresa empresa, double valor) {
        if (valor <= 0) {
            throw new RuntimeException("O valor do depósito deve ser maior que zero.");
        }

        double saldoAtual = empresa.getSaldo();
        double novoSaldo = saldoAtual + valor;

        // Atualize o saldo da empresa
        empresa.setSaldo(novoSaldo);

        // Salve a empresa no repositório (ou realize qualquer ação necessária)
        empresaRepository.save(empresa);
    }

    private void realizarSaque(Empresa empresa, double valor) {
        if (valor <= 0) {
            throw new RuntimeException("O valor do saque deve ser maior que zero.");
        }

        double saldoAtual = empresa.getSaldo();

        if (valor > saldoAtual) {
            throw new RuntimeException("Saldo insuficiente para o saque.");
        }

        double novoSaldo = saldoAtual - valor;

        // Atualize o saldo da empresa
        empresa.setSaldo(novoSaldo);

        // Salve a empresa no repositório (ou realize qualquer ação necessária)
        empresaRepository.save(empresa);
    }

}
