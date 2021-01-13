# Pong 2.0

TsuHub

![Pong2](https://github.com/TsuHub/Pong-Game-2/blob/master/Pong2.jpg?raw=true)

## To Run

<p align="left">

	You need to have jdk installed to run the game.
	
	To run the game at the terminal, go to the src path
	containing the Pong 2.0 game files. After the previous
	step, do the following steps:
	
	1 - To compile all codes:
	$ javac *.java
	
	2 - And then, to run the game with the trail effects:
	$ java Pong FxBall
	
	-------------------------------------------------------------

	Para a execu��o do programa, no terminal, ir at� o
	diret�rio src que cont�m os arquivos do programa Pong 2.0.
	Ap�s o passo anterior, ainda no terminal, seguir os seguintes
	passos:

	1 - Para compilar, dar o comando:
	$ javac *.java

	2 - Para execu��o do programa, dar o comando:
	$ java Pong FxBall
	
</p>


## More Details

<p align="left">

	� necess�rio que voc� possua o JDK para o funcionamento do jogo.

	Em rela��o ao par�metro intervalo_de_tempo , pode-se us�-lo para especificar o
	intervalo de tempo m�nimo (em milissegundos) que deve ser aguardado entre o
	processamento de dois frames consecutivos do jogo. Este par�metro possui valor padr�o
	igual a 3 e, em geral, n�o h� raz�o para alter�-lo.

	Por fim, o par�metro safe_mode , quando definido como true , ativa o modo de seguran�a
	do modo gr�fico implementado pela classe GameLib . Este par�metro possui valor padr�o
	igual a false , e deve ser usado caso a tela do jogo n�o seja exibida de forma correta
	usando o modo padr�o (que � mais eficiente quando funciona adequadamente).

	A linha abaixo ilustra como executar o jogo especificando todos os 3 par�metros opcionais
	(no caso, para usar a bola implementada pela classe DiamondBall, determinar o intervalo de
	tempo entre dois frames como 5 milissegundos e habilitar o safe mode):

	$ java Pong FxBall 3 true

</p>

## Input

<table>
  <tr>
    <th>Action</th><th>Keys to Player 1</th><th>Keys to Player 2</th>
  </tr>
  <tr>
    <td>Up</td><td>A</td><td>K</td>
  </tr>
  <tr>
    <td>Down</td><td>Z</td><td>M</td>
  </tr>
  <tr>
    <td>Start</td><td>Espace bar</td>
  </tr>
</table>

## Game Libraries Used

GameLib.class powered by Fl�vio Luiz Coutinho.