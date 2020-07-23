TsuHub

Para a execução do programa, no terminal, ir até o
diretório src que contêm os arquivos do programa Pong 2.0.
Após o passo anterior, ainda no terminal, seguir os seguintes
passos:

1 - Para compilar, dar o comando:
$ javac *.java

2 - Para execução do programa, dar o comando:
$ java Pong FxBall

=============================================================

MAIS DETALHES

Em relação ao parâmetro intervalo_de_tempo , pode-se usá-lo para especificar o
intervalo de tempo mínimo (em milissegundos) que deve ser aguardado entre o
processamento de dois frames consecutivos do jogo. Este parâmetro possui valor padrão
igual a 3 e, em geral, não há razão para alterá-lo.

Por fim, o parâmetro safe_mode , quando definido como true , ativa o modo de segurança
do modo gráfico implementado pela classe GameLib . Este parâmetro possui valor padrão
igual a false , e deve ser usado caso a tela do jogo não seja exibida de forma correta
usando o modo padrão (que é mais eficiente quando funciona adequadamente).

A linha abaixo ilustra como executar o jogo especificando todos os 3 parâmetros opcionais (no caso, para usar a bola implementada pela classe DiamondBall,
determinar o intervalo de tempo entre dois frames como 5 milissegundos e habilitar o safe mode):

$ java Pong FxBall 3 true