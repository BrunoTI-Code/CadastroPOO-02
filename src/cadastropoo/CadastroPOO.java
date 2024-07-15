package cadastropoo;

import model.*;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CadastroPOO {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PessoaFisicaRepo repoFisica = new PessoaFisicaRepo();
        PessoaJuridicaRepo repoJuridica = new PessoaJuridicaRepo();

        while (true) {
            System.out.println("1. Incluir");
            System.out.println("2. Alterar");
            System.out.println("3. Excluir");
            System.out.println("4. Exibir pelo ID");
            System.out.println("5. Exibir todos");
            System.out.println("6. Salvar dados");
            System.out.println("7. Recuperar dados");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opcao: ");
            int opcao = lerInt(scanner);

            if (opcao == 0) {
                break;
            }

            switch (opcao) {
                case 1:
                    System.out.println("1. Pessoa Fisica");
                    System.out.println("2. Pessoa Juridica");
                    int tipo = lerInt(scanner);

                    if (tipo == 1) {
                        System.out.print("ID: ");
                        int id = lerInt(scanner);
                        scanner.nextLine();  // Consumir nova linha
                        System.out.print("Nome: ");
                        String nome = scanner.nextLine();
                        System.out.print("CPF: ");
                        String cpf = scanner.nextLine();
                        System.out.print("Idade: ");
                        int idade = lerInt(scanner);
                        scanner.nextLine();

                        repoFisica.inserir(new PessoaFisica(id, nome, cpf, idade));
                        System.out.println("Pessoa Fisica adicionada com sucesso.");
                    } else if (tipo == 2) {
                        System.out.print("ID: ");
                        int id = lerInt(scanner);
                        scanner.nextLine();  // Consumir nova linha
                        System.out.print("Nome: ");
                        String nome = scanner.nextLine();
                        System.out.print("CNPJ: ");
                        String cnpj = scanner.nextLine();

                        repoJuridica.inserir(new PessoaJuridica(id, nome, cnpj));
                        System.out.println("Pessoa Juridica adicionada com sucesso.");
                    }
                    break;
                case 2:
                    System.out.println("1. Pessoa Fisica");
                    System.out.println("2. Pessoa Juridica");
                    tipo = lerInt(scanner);

                    if (tipo == 1) {
                        System.out.print("ID: ");
                        int id = lerInt(scanner);
                        scanner.nextLine();
                        PessoaFisica pf = repoFisica.obter(id);
                        if (pf != null) {
                            System.out.print("Nome (" + pf.getNome() + "): ");
                            String nome = scanner.nextLine();
                            System.out.print("CPF (" + pf.getCpf() + "): ");
                            String cpf = scanner.nextLine();
                            System.out.print("Idade (" + pf.getIdade() + "): ");
                            int idade = lerInt(scanner);
                            scanner.nextLine();

                            pf.setNome(nome.isEmpty() ? pf.getNome() : nome);
                            pf.setCpf(cpf.isEmpty() ? pf.getCpf() : cpf);
                            pf.setIdade(idade == 0 ? pf.getIdade() : idade);
                            repoFisica.alterar(pf);
                            System.out.println("Pessoa Fisica alterada com sucesso.");
                        } else {
                            System.out.println("Pessoa Fisica não encontrada.");
                        }
                    } else if (tipo == 2) {
                        System.out.print("ID: ");
                        int id = lerInt(scanner);
                        scanner.nextLine();
                        PessoaJuridica pj = repoJuridica.obter(id);
                        if (pj != null) {
                            System.out.print("Nome (" + pj.getNome() + "): ");
                            String nome = scanner.nextLine();
                            System.out.print("CNPJ (" + pj.getCnpj() + "): ");
                            String cnpj = scanner.nextLine();

                            pj.setNome(nome.isEmpty() ? pj.getNome() : nome);
                            pj.setCnpj(cnpj.isEmpty() ? pj.getCnpj() : cnpj);
                            repoJuridica.alterar(pj);
                            System.out.println("Pessoa Juridica alterada com sucesso.");
                        } else {
                            System.out.println("Pessoa Juridica não encontrada.");
                        }
                    }
                    break;
                case 3:
                    System.out.println("1. Pessoa Fisica");
                    System.out.println("2. Pessoa Juridica");
                    tipo = lerInt(scanner);

                    System.out.print("ID: ");
                    int id = lerInt(scanner);
                    scanner.nextLine();

                    if (tipo == 1) {
                        repoFisica.excluir(id);
                        System.out.println("Pessoa Fisica excluída com sucesso.");
                    } else if (tipo == 2) {
                        repoJuridica.excluir(id);
                        System.out.println("Pessoa Juridica excluída com sucesso.");
                    }
                    break;
                case 4:
                    System.out.println("1. Pessoa Fisica");
                    System.out.println("2. Pessoa Juridica");
                    tipo = lerInt(scanner);

                    System.out.print("ID: ");
                    id = lerInt(scanner);
                    scanner.nextLine();

                    if (tipo == 1) {
                        PessoaFisica pf = repoFisica.obter(id);
                        if (pf != null) {
                            pf.exibir();
                        } else {
                            System.out.println("Pessoa Fisica não encontrada.");
                        }
                    } else if (tipo == 2) {
                        PessoaJuridica pj = repoJuridica.obter(id);
                        if (pj != null) {
                            pj.exibir();
                        } else {
                            System.out.println("Pessoa Juridica não encontrada.");
                        }
                    }
                    break;
                case 5:
                    System.out.println("1. Pessoa Fisica");
                    System.out.println("2. Pessoa Juridica");
                    tipo = lerInt(scanner);

                    if (tipo == 1) {
                        for (PessoaFisica pf : repoFisica.obterTodos()) {
                            pf.exibir();
                        }
                    } else if (tipo == 2) {
                        for (PessoaJuridica pj : repoJuridica.obterTodos()) {
                            pj.exibir();
                        }
                    }
                    break;
                case 6:
                    System.out.print("Prefixo do arquivo: ");
                    String prefixo = scanner.nextLine();

                    try {
                        repoFisica.persistir(prefixo + ".fisica.bin");
                        repoJuridica.persistir(prefixo + ".juridica.bin");
                        System.out.println("Dados salvos com sucesso.");
                    } catch (IOException e) {
                        System.out.println("Erro ao salvar os dados: " + e.getMessage());
                    }
                    break;
                case 7:
                    System.out.print("Prefixo do arquivo: ");
                    prefixo = scanner.nextLine();

                    try {
                        repoFisica.recuperar(prefixo + ".fisica.bin");
                        repoJuridica.recuperar(prefixo + ".juridica.bin");
                        System.out.println("Dados recuperados com sucesso.");
                    } catch (IOException | ClassNotFoundException e) {
                        System.out.println("Erro ao recuperar os dados: " + e.getMessage());
                    }
                    break;
                default:
                    System.out.println("Opção invalida.");
                    break;
            }
        }

        scanner.close();
    }

    private static int lerInt(Scanner scanner) {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.print("Entrada invalida. Digite um numero inteiro: ");
                scanner.next();  // Consumir a entrada inválida
            }
        }
    }
}
