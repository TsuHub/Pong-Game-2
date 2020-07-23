TsuHub

Para a execu��o do programa, no terminal, ir at� o
diret�rio src que cont�m os arquivos do programa Pong 2.0.
Ap�s o passo anterior, ainda no terminal, seguir os seguintes
passos:

1 - Para compilar, dar o comando:
$ javac *.java

2 - Para execu��o do programa, dar o comando:
$ java Pong FxBall

=============================================================

MAIS DETALHES

Em rela��o ao par�metro intervalo_de_tempo , pode-se us�-lo para especificar o
intervalo de tempo m�nimo (em milissegundos) que deve ser aguardado entre o
processamento de dois frames consecutivos do jogo. Este par�metro possui valor padr�o
igual a 3 e, em geral, n�o h� raz�o para alter�-lo.

Por fim, o par�metro safe_mode , quando definido como true , ativa o modo de seguran�a
do modo gr�fico implementado pela classe GameLib . Este par�metro possui valor padr�o
igual a false , e deve ser usado caso a tela do jogo n�o seja exibida de forma correta
usando o modo padr�o (que � mais eficiente quando funciona adequadamente).

A linha abaixo ilustra como executar o jogo especificando todos os 3 par�metros opcionais (no caso, para usar a bola implementada pela classe DiamondBall,
determinar o intervalo de tempo entre dois frames como 5 milissegundos e habilitar o safe mode):

$ java Pong FxBall 3 true