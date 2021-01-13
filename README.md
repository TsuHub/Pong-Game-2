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

	Para a execução do programa, no terminal, ir até o
	diretório src que contêm os arquivos do programa Pong 2.0.
	Após o passo anterior, ainda no terminal, seguir os seguintes
	passos:

	1 - Para compilar, dar o comando:
	$ javac *.java

	2 - Para execução do programa, dar o comando:
	$ java Pong FxBall
	
</p>


## More Details

<p align="left">

	É necessário que você possua o JDK para o funcionamento do jogo.

	Em relação ao parâmetro intervalo_de_tempo , pode-se usá-lo para especificar o
	intervalo de tempo mínimo (em milissegundos) que deve ser aguardado entre o
	processamento de dois frames consecutivos do jogo. Este parâmetro possui valor padrão
	igual a 3 e, em geral, não há razão para alterá-lo.

	Por fim, o parâmetro safe_mode , quando definido como true , ativa o modo de segurança
	do modo gráfico implementado pela classe GameLib . Este parâmetro possui valor padrão
	igual a false , e deve ser usado caso a tela do jogo não seja exibida de forma correta
	usando o modo padrão (que é mais eficiente quando funciona adequadamente).

	A linha abaixo ilustra como executar o jogo especificando todos os 3 parâmetros opcionais
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

GameLib.class powered by Flávio Luiz Coutinho.