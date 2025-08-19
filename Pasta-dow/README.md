            ###LOGIC-LAB - Sistema de Gestão de Usuários e Relatórios###

*Descrição
O Logic-Lab é um sistema em Java desenvolvido para gerenciar usuários (alunos e professores) e gerar relatórios de acesso. Ele utiliza uma interface de linha de comando, permitindo autenticação, atualização de dados, geração de relatórios e gerenciamento do sistema.

*Objetivo
O objetivo principal do Logic-Lab é fornecer uma solução simples para:
    -Gerenciamento de usuários: Professores podem adicionar, remover e visualizar dados de usuários.
    -Geração de relatórios: Professores podem acessar logs de uso e gerar relatórios personalizados.
    -Autenticação: Controle de acesso por meio de login e senha para alunos e professores.
    -Ambiente de Execução
*Sistema Operacional: Windows

*Estrutura de Pastas:
logic-lab/
├── src/          (Código fonte)
├── bin/          (Arquivos compilados)
└── README.md     (Este arquivo)

*Arquivos
Fonte: Todos os arquivos .java estão localizados na pasta src/, organizados em pacotes:

main: Contém a classe principal App.java.
models: Classes de dados como Usuario, Aluno, e Professor.
service: Serviços para manipular dados e gerar relatórios.
view: Classes de interface para interagir com o usuário.
Executável: Após compilar o programa, os arquivos .class serão gerados na pasta bin/.

*Instruções de Uso
1-Compilar o Programa
2-Navegue até a raiz do projeto:
-cd /caminho/para/logic-lab/
3-Compile todos os arquivos .java:
-javac -d bin src/main/*.java src/models/*.java src/service/*.java src/view/*.java
-java -cp bin main.App

*Funcionalidades
1. Autenticação
Usuários (alunos e professores) podem autenticar-se com login e senha.
Professores têm permissões administrativas, enquanto alunos têm permissões limitadas.
2. Menu Principal
Opções disponíveis após autenticação:
Atualizar Dados (Aluno): Permite que alunos atualizem suas informações pessoais.
Gerar Relatório (Professor): Professores podem acessar relatórios diários, semanais e personalizados.
Resetar Sistema (Professor): Professores podem limpar todos os dados do sistema.
Sair: Retorna ao menu inicial ou encerra o programa.
3. Geração de Relatórios
Professores podem visualizar:
Relatórios diários e semanais.
Acessos por usuário em períodos específicos.
4. Resetar Sistema
Apenas professores podem limpar dados e logs do sistema.

Att, Wemerson