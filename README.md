# Documentação
## Relatorio do projeto
### Detalhamento técnico do projeto
<p>A principal tecnologia a ser utilizada na nossa proposta é Java, a linguagem de programação referência deste projeto. Java é uma linguagem de programação orientada a objetos desenvolvida na década de 90 por uma equipe de programadores chefiada por James Gosling, na empresa Sun Microsystems, que em 2008 foi adquirido pela empresa Oracle Corporation. Diferente das linguagens de programação modernas, que são compiladas para código nativo, Java é compilada para um bytecode que é interpretado por uma máquina virtual (Java Virtual Machine, JVM). 
A linguagem Java foi projetada tendo em vista os seguintes objetivos:

  * Orientação a Objetos - Baseado no modelo de simular;
  * Portabilidade - Independência de plataforma - "escreva uma vez, execute em qualquer lugar" (_"write once, run anywhere"_);
  * Recursos de Rede - Possui extensa biblioteca de rotinas que facilitam a cooperação com protocolos _TCP/IP_, _HTTP_ e _FTP_;
  * Segurança - Pode executar programas via rede com restrições de execução.

  <p>Para o desenvolvimento do sistema, além do Java, utilizamos o Intellij IDEA como interface de desenvolvimento. IntelliJ IDEA é um ambiente de desenvolvimento de software de computador escrito em Java, Kotlin, Groovy e outras linguagens baseadas em JVM.</p>
  <p>A primeira versão do IntelliJ IDEA foi lançada em janeiro de 2001 e foi um dos primeiros IDE Java disponíveis com recursos avançados de navegação e refatoração de código integrados. Em 2009, a JetBrains lançou o código-fonte do IntelliJ IDEA sob a licença Apache 2.0 de código aberto. A JetBrains também começou a distribuir uma versão limitada do IntelliJ IDEA que consiste em recursos de código aberto sob o apelido de Community Edition. A Ultimate Edition oferece recursos adicionais por uma taxa mensal. </p>
  <p>Para o desenvolvimento efetivo dessa aplicação, identificamos as seguintes classes como essenciais: Gerente(Administrador), Cliente, Funcionário e Medicamento.</p>
  <p>Esse sistema visa otimizar a organização e o controle de dados na gestão farmacêutica, possibilitando uma administração mais eficiente e precisa dos recursos.</p>
  <p>Cada classe desempenha um papel específico, refletindo as diferentes funções desempenhadas por gerentes, clientes, farmacêuticos e demais funcionários dentro do contexto de uma farmácia.</p>
  As operações e classes citadas anteriormente são essenciais para um bom funcionamento da nossa proposta. No entanto, temos as funcionalidades mínimas e necessárias de cada classe individualmente:</p>
  
  - A Classe Medicamento é um componente fundamental no contexto do sistema farmacêutico proposto. Ela é projetada para armazenar e gerenciar todas as informações relevantes relacionadas aos medicamentos disponíveis na farmácia. Cada instânciadesta classe representa um medicamento específico, agregando dados cruciais para uma administração eficaz do estoque e atendimento aos clientes.
  - A Classe Farmácia desempenha um papel central no sistema proposto, servindo como a entidade principal responsável por coordenar e integrar diversas operações dentro do contexto farmacêutico. Essa classe é projetada para abranger informações e funcionalidades relacionadas à administração global da farmácia.
  - A Classe Funcionário é uma componente crucial no sistema farmacêutico proposto, representando os profissionais que desempenham diversos papéis dentro da farmácia. Ela é responsável por armazenar informações específicas de cada colaborador, facilitando a administração de recursos humanos.
  - A Classe Gerente, que herda as características da Classe Funcionário, desempenha um papel específico e crucial dentro do contexto farmacêutico. Ao herdar atributos e métodos da Classe Funcionário, ela mantém a estrutura geral de informações relacionadas a funcionários, enquanto também incorpora elementos exclusivos à função de gerente.

  <p>Esta foi a apresentação básica do nosso trabalho. Todas as classes, atributos e métodos que puderam ser visualizados anteriormente, são requisitos mínimos de funcionamento do nosso sistema. Assim, tentaremos viabilizar a implementação de funcionalidades extras, além de uma interface gráfica eficaz para a utilização dos recursos oferecidos.</p>

## Relatorio de grupo
### 1. DIAGNÓSTICO E TEORIZAÇÃO 
#### 1.1. Identificação das partes interessadas e parceiros 
  A criação e desenvolvimento do projeto contou com a participação ativa de 5 pessoas no total 
sendo:
1. Adalberto Silva Santana <sup>[GitHub](https://github.com/Takushi27)</sup>
    - Escolaridade: Ensino Médio
    - Faixa Etária: 20 anos
    - Gênero: Masculino
    - Perfil Socioeconômico: Estudante

2. Antônio André Amorim de Oliveira <sup>[GitHub](https://github.com/antonioabatte)</sup>
    - Escolaridade: Ensino Médio
    - Faixa Etária: 19 anos
    - Gênero: Masculino
    - Perfil Socioeconômico: Estudante

3. Igor Cardoso Barradas <sup>[GitHub](https://github.com/RoguiConor)</sup>
    - Escolaridade: Ensino Médio
    - Faixa Etária: 27 anos
    - Gênero: Masculino
    - Perfil Socioeconômico: Estudante
    
4. Luis Felipe Macêdo Barbosa <sup>[GitHub](https://github.com/yegear1)</sup>
    - Escolaridade: Ensino Médio
    - Faixa Etária: 19 anos
    - Gênero: Masculino
    - Perfil Socioeconômico: Estudante
    
5. Wallas Aguiar Rocha <sup>[GitHub](https://github.com/WallasAR)</sup>
    - Escolaridade: Ensino Médio
    - Faixa Etária: 20 anos
    - Gênero: Masculino
    - Perfil Socioeconômico: Estudante
    

#### 1.2. Problemática e Motivação acadêmica
  <p>A implementação de um projeto de farmácia em uma escola do ensino médio é motivada pela busca de enriquecer a experiência acadêmica dos alunos, proporcionando uma imersão prática em um campo profissional relevante e dinâmico.</p>
  <p>A automação de atividades e gestão são alguns dos pontos que têm maior ganhomercadológico para o gestor. A tendência para o ramo do varejo farmacêutico continua combastante concorrência econômica, levando as empresas a enfrentarem maiores desafios paramanter a competitividade. A tecnologia tem sido uma grande aliada dos empreendedores para concluir o controle e o gerenciamento do negócio, pois além de dinamizar o trabalho, faz comque toda o grupo tenha mais tempo disponível para se dedicar às questões estratégicas e poucos operacionais.</p>

#### 1.3. Justificativa
  <p>A proposta visa promover o desenvolvimento de habilidades práticas essenciais, desde o atendimento ao cliente até a compreensão dos processos regulatórios e éticos envolvidos na farmácia. Essas habilidades não apenas enriquecem a formação acadêmica, mas também preparam os alunos para desafios profissionais futuros.</p>
  <p>A farmácia é um campo profissional fundamental e impactante na sociedade. Introduzir os alunos a aspectos práticos dessa profissão desde o ensino médio não apenas informa sobre futuras escolhas de carreira, mas também destaca a relevância social do papel do farmacêutico através da tecnologia.</p>

#### 1.4. Objetivos/resultados/efeitos a serem alcançados (em relação ao problema identificado e sob a perspectiva dos públicos envolvidos)
**IMPLEMENTAÇÃO DO SISTEMA DE GERENCIAMENTO DE FARMÁCIA NA ESCOLA: BENEFÍCIOS E OBJETIVOS**
1. Controle de Estoque Eficiente:
- A introdução do sistema visa garantir uma gestão mais precisa e eficiente dos medicamentos e materiais de saúde na farmácia escolar.
- Ao monitorar o estoque de maneira automatizada, a escola pode prevenir situações de escassez, assegurando que itens essenciais estejam sempre disponíveis para atender às necessidades dos alunos e funcionários.
- Simultaneamente, o sistema contribui para evitar o excesso de estoque, promovendo uma alocação mais eficiente dos recursos financeiros da instituição.

2. Registro Eletrônico de Funcionários:
- Criar um módulo no sistema de gerenciamento de farmácia para efetuar o registro eletrônico de funcionários, incluindo informações pessoais, dados de contato e documentos necessários.
- Implementar ferramentas de busca rápida e eficiente, permitindo o acesso fácil e ágil às informações dos funcionários durante processos de seleção, avaliação de desempenho e gerenciamento de recursos humanos.
- Desenvolver funcionalidades que permitam a fácil atualização e manutenção dos registros, garantindo que as informações estejam sempre precisas e em conformidade com as mudanças na equipe.

3. Desenvolvimento de uma Interface Intuitiva:
- Desenvolver uma interface intuitiva e acessível no sistema de gerenciamento de farmácia que forneça informações detalhadas sobre cada medicamento, incluindo dosagens, indicações e possíveis interações.
- Implementar funcionalidades de busca eficientes para permitir que os usuários acessem rapidamente informações sobre medicamentos, contribuindo para uma compreensão mais fácil e ágil.
  <p>Ao unir esses objetivos, a escola não apenas moderniza seus processos internos, mas também promove uma cultura de cuidado e responsabilidade dentro da comunidade escolar. O sistema de gerenciamento de farmácia, fundamentado na tecnologia e ciências da computação, não só otimiza a administração de recursos, mas também contribui para a criação de um ambiente escolar mais saudável e eficaz. Essa abordagem integrada reflete o compromisso da instituição em oferecer serviços de saúde de qualidade, alinhados às práticas contemporâneas e às demandas da comunidade educacional.</p>

#### 1.5. Referencial teórico
- DEITEL, P. J.; DEITEL, H. M. Java: como programar. 10. ed. São Paulo: Pearson, 2017.
- NAUGTHON, Patrick. Dominando o Java, Guia Autorizado da Sun Microsystems.[S.l.]: Editora Makron Books. 1997.
- VERSOLATTO, Fabio. Sistemas orientados a objetos: conceitos e práticas. 1. ed. Rio de Janeiro: Freitas Bastos, 2023.

### 2. PLANEJAMENTO E DESENVOLVIMENTO DO PROJETO
#### 2.1. Grupo de trabalho (descrição da responsabilidade de cada membro)
Cada membro do grupo foi responsável por uma parte da projeção, desenvolvimento, testamento e implementação para a criação do trabalho.Foram divididas as tarefas sendo:
  - Luiz, Adalberto e Antônio foram os responsáveis por métodos e classes atribuídas a funções para uma farmácia, métodos como compra, receita e vendas, além de classes como medicamento, farmácia e funcionário.
  - Adalberto e Wallas foram os responsáveis pela utilização do banco de dados a parte da aplicação do _software MySql_.
  - Wallas, Luiz e Igor tiveram a função da criação da interface gráfica a partir do _Framework Javafx_.
  - Luiz, Antônio, Igor, Adalberto e Wallas a integração e utilização no console com todos os métodos e classes organizados dentro da função principal.
  <p>Luiz, Antônio, Igor, Adalberto e Wallas obrigatoriamente todos participaram da criação da documentação e relatório.</p>

#### 2.3. Metas, critérios ou indicadores de avaliação do projeto
1. Controle de Estoque Eficiente: Detalhamento do Projeto
- Identificação de categorias de produtos.
- Criação de interface intuitiva para registros.
- Integração de alertas automáticos.

2. Desenvolvimento do Módulo de Registro Eletrônico:
- Identificação dos dados essenciais para registro (informações pessoais, contato, documentos).
- Desenvolvimento de uma interface amigável para o registro eletrônico de funcionários.
- Implementação de validações para garantir a precisão dos dados inseridos.

3. Implementação da Interface no Sistema:
- Tradução dos protótipos aprovados em um design funcional dentro do sistema.
- Garantia de consistência visual e de interação em toda a interface.
- Testes finais para verificar a integração e usabilidade da interface no ambiente real.

### 3. ENCERRAMENTO DO PROJETO
#### 3.1. Relato Coletivo: 
<p>Houve uma percepção positiva por parte da comunidade escolar em relação às melhorias implementadas. A facilidade de acesso a informações sobre medicamentos, o controle eficiente de estoque e a segurança dos dados dos alunos e funcionários foram aspectos destacados nas interações com os usuários finais.</p>
