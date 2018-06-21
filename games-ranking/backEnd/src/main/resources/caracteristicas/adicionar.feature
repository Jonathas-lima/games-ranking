  @SalvarTeste
  Feature: Testar a operacao de salvar jogador no banco de dados

    O sistema deve salvar o jogador no banco de dados de forma correta.
    Seguindo as seguintes restrições:
    1)O nome do jogador so pode conter caracteres alfanumericos e o espaco
    2)O valor de vitorias e partidas nao pode ser negativos
    3)O valor de vitorias nao pode ser maior que o de partidas

    Scenario Outline: Testar salvar jogador
      Given o usuario acessa o sistema
      And digita o nome do jogador "<nome>"
      And digita no campo partidas o numero de <partidas>
      And digita no campo vitorias o numero de <vitorias>
      Then o usuario tera criado um jogador no banco de dados

Examples:
|nome|partidas|vitorias|
|jose|1       |1       |