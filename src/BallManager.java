/*===========*
*			 *
*	TsuHub   *
*			 *
=============*/

import java.awt.Color;
import java.lang.reflect.*;
import java.util.Random;
import java.util.ArrayList;

/**
 Classe que gerencia uma ou mais bolas presentes em uma partida. Esta classe é a responsável por instanciar
 e gerenciar a bola principal do jogo (aquela que existe desde o ínicio de uma partida), assim como eventuais
 bolas extras que apareçam no decorrer da partida. Esta classe também deve gerenciar a interação da(s) bola(s)
 com os alvos, bem como a aplicação dos efeitos produzidos para cada tipo de alvo atingido.
 */

public class BallManager
{
	/**
	 * Atributo privado que representa a bola principal do jogo.
	 */

	private IBall theBall = null;

	/**
	 * Atributo privado que representa o tipo (classe) das instâncias de bola que serão criadas por esta classe.
	 */

	private Class<?> ballClass = null;

	/**
	 * Atributo privado que guarda a velocidade padrão, para que esta seja replicada para as bolas duplicadas.
	 */
	private static double defaultSpeed;

	/**
	 * (duplicated Ball List)
	 * Um ArrayList, do tipo IBall, que receberá as bolas duplicadas. Cada uma das bolas
	 * duplicadas da lista, receberá um construtor da classe que implementa IBall, dependendo
	 * do argumento de entrada para a execução pelo terminal.
	 */
	private ArrayList<IBall> d_BallList;

	/**
	 * Definição da cor para as bolas duplicadas.
	 */
	private Color fadeOutRed = new Color(255,0,0,255);

	/**
	 * Atributo privado utilizado para decrementar o parâmetro Alpha da cor da bola,
	 * gerando um efeito "FadeOut".
	 */
	private int fadeCounter = 1;

	/**
	 * Atributo privado que faz o controle do incremento da variável fadeCounter, para
	 * que o efeito "FadeOut" ocorra de forma lenta.
	 */
	private int receiveFadeCounter;

	/**
	 * Um ArrayList de objetos BallManager. Cada um dos objetos possui um fadeCounter e
	 * um receiveFadeCounter associadas a cada uma das bolas, i.e., um objeto responsável
	 * por fazer o controle do efeito "FadeOut" a cada uma das bolas.
	 */
	private ArrayList<BallManager> fadeController;

	private double vetoresXY[] = new double[2];
	private double vetorNormalizado[] = new double[2];

	/**
	 * Construtor da classe BallManager.
	 *
	 * @param className nome da classe que define o tipo das instâncias de bola que serão criadas por esta classe.
	 */
	public BallManager(String className) {
		d_BallList = new ArrayList<IBall> ();
		fadeController = new ArrayList<BallManager>();

		try {
			ballClass = Class.forName(className);
		} catch (Exception e) {
			System.out.println("Classe '" + className + "' não reconhecida... Usando 'Ball' como classe padrão.");
			ballClass = Ball.class;
		}
	}

	/**
	 * Recebe as componetes x e y de um vetor, e devolve as componentes x e y do vetor normalizado (isto é, com comprimento igual a 1.0).
	 *
	 * @param x componente x de um vetor que representa uma direção.
	 * @param y componente y de um vetor que represetna uma direção.
	 * @return array contendo dois valores double que representam as componentes x (índice 0) e y (índice 1) do vetor normalizado (unitário).
	 */
	private double[] normalize(double x, double y) {
		double length = Math.sqrt(x * x + y * y);
		return new double[]{x / length, y / length};
	}

	/**
	 * Cria uma instancia de bola, a partir do tipo (classe) cujo nome foi passado ao construtor desta classe.
	 * O vetor direção definido por (vx, vy) não precisa estar normalizado. A implemntação do método se encarrega
	 * de fazer a normalização.
	 *
	 * @param cx     coordenada x da posição inicial da bola (centro do retangulo que a representa).
	 * @param cy     coordenada y da posição inicial da bola (centro do retangulo que a representa).
	 * @param width  largura do retangulo que representa a bola.
	 * @param height altura do retangulo que representa a bola.
	 * @param color  cor da bola.
	 * @param speed  velocidade da bola (em pixels por millisegundo).
	 * @param vx     componente x do vetor (não precisa ser unitário) que representa a direção da bola.
	 * @param vy     componente y do vetor (não precisa ser unitário) que representa a direção da bola.
	 */
	private IBall createBallInstance(double cx, double cy, double width, double height, Color color, double speed, double vx, double vy) {
		IBall ball = null;
		double[] v = normalize(vx, vy);

		try {
			Constructor<?> constructor = ballClass.getConstructors()[0];
			ball = (IBall) constructor.newInstance(cx, cy, width, height, color, speed, v[0], v[1]);
		} catch (Exception e) {
			System.out.println("Falha na instanciação da bola do tipo '" + ballClass.getName() + "' ... Instanciando bola do tipo 'Ball'");
			ball = new Ball(cx, cy, width, height, color, speed, v[0], v[1]);
		}
		return ball;
	}

	/**
	 * Cria a bola principal do jogo. Este método é chamado pela classe Pong, que contem uma instância de BallManager.
	 *
	 * @param cx     coordenada x da posição inicial da bola (centro do retangulo que a representa).
	 * @param cy     coordenada y da posição inicial da bola (centro do retangulo que a representa).
	 * @param width  largura do retangulo que representa a bola.
	 * @param height altura do retangulo que representa a bola.
	 * @param color  cor da bola.
	 * @param speed  velocidade da bola (em pixels por millisegundo).
	 * @param vx     componente x do vetor (não precisa ser unitário) que representa a direção da bola.
	 * @param vy     componente y do vetor (não precisa ser unitário) que representa a direção da bola.
	 */
	public void initMainBall(double cx, double cy, double width, double height, Color color, double speed, double vx, double vy) {
		theBall = createBallInstance(cx, cy, width, height, color, speed, vx, vy);
		defaultSpeed = theBall.getSpeed();    			// Guarda a velocidade padrão da bola theBall
	}

	/**
	 * Método que desenha todas as bolas gerenciadas pela instância de BallManager.
	 * Chamado sempre que a(s) bola(s) precisa ser (re)desenhada(s).
	 *
	 * Se a lista d_BallList não estiver vazia e houver um próximo elemento na lista,
	 * há ocorrência do percorrimento da lista para que cada uma das bolas sejam
	 * (re)desenhadas. A verificação para o fadeController não precisa ser feita,
	 * já que existe sincronia entre os ArrayList.
	 */
	public void draw() {
		theBall.draw();
		for ( int i = 0; i < d_BallList.size(); i++ ) d_BallList.get(i).draw();
	}

	/**
	 * Método que atualiza todas as bolas gerenciadas pela instância de BallManager, em decorrência da passagem do tempo.
	 *
	 * Um laço foi adicionado para que ocorra a atualização de cada um dos elementos do ArrayList.
	 * A atualização ocorre de forma vagarosa, devido ao receiveFadeCounter que faz esse controle.
	 * O fator alpha da bola, só ocorre após 4 updates.
	 *
	 * @param delta quantidade de millisegundos que se passou entre o ciclo anterior de atualização do jogo e o atual.
	 */
	public void update(long delta) {
		theBall.update(delta);

		for ( int i = 0; i < d_BallList.size(); i++ ) {
			d_BallList.get(i).update( delta );
			fadingOut(d_BallList.get(i), fadeController.get(i).fadeCounter);

			if ( fadeController.get(i).receiveFadeCounter == 4 ) {
				if (fadeController.get(i).fadeCounter > 0) fadeController.get(i).fadeCounter += 1;
				fadeController.get(i).receiveFadeCounter = 0;
			}
			fadeController.get(i).receiveFadeCounter++;

			ballsQuantity();
		}
	}

	/**
	 * Método que processa as colisões entre as bolas gerenciadas pela instância de BallManager com uma parede.
	 *
	 * @param wall referência para uma instância de Wall para a qual será verificada a ocorrência de colisões.
	 * @return um valor int que indica quantas bolas colidiram com a parede (uma vez que é possível que mais de
	 * uma bola tenha entrado em contato com a parede ao mesmo tempo).
	 */
	public int checkCollision( Wall wall ) {
		int hits = 0;
		if ( theBall.checkCollision( wall ) ) hits++;

		for ( IBall b : d_BallList ) {
			if ( b.checkCollision( wall ) ) hits++;
		}
		return hits;
	}

	/**
	 * Método que processa as colisões entre as bolas gerenciadas pela instância de BallManager com um player.
	 *
	 * @param player referência para uma instância de Player para a qual será verificada a ocorrência de colisões.
	 */
	public void checkCollision(Player player) {
		theBall.checkCollision(player);
		for (IBall b : d_BallList) b.checkCollision( player );
	}

	/**
	 * Método que processa as colisões entre as bolas gerenciadas pela instância de BallManager com um alvo.
	 *
	 * Adicionado o processamento das colisões das bolas duplicadas com o alvo.
	 *
	 * @param target referência para uma instância de Target para a qual será verificada a ocorrência de colisões.
	 */
	public void checkCollision(Target target){
		if (theBall.checkCollision(target)){
			if ( target instanceof BoostTarget && theBall.getSpeed() == defaultSpeed ) {
				theBall.setSpeed(BoostTarget.BOOST_FACTOR);
				boostDuration( theBall );
			}
			if (target instanceof DuplicatorTarget)	createDuplicatedBall(theBall, target);
		}

		for ( int i = 0; i < d_BallList.size(); i++ ) {
			if ( d_BallList.get(i).checkCollision( target ) ) {
				if (target instanceof BoostTarget && d_BallList.get(i).getSpeed() == defaultSpeed) {
					d_BallList.get(i).setSpeed(BoostTarget.BOOST_FACTOR);
					boostDuration(d_BallList.get(i));
				}
				if (target instanceof DuplicatorTarget)	createDuplicatedBall(d_BallList.get(i), target);
			}
		}
	}

	/**
	 * Método responsável por criar o efeito "FadeOut" nas bolas duplicadas.
	 * @param ball referência para uma instância de IBall, independente do tipo do construtor recebido pelo objeto.
	 * @param a inteiro responsável por decrementar o fator alpha da bola. O seu aumento resulta em uma maior tranparência.
	 */
	private void fadingOut(IBall ball, int a) {
		fadeOutRed = new Color(ball.getColor().getRed(), ball.getColor().getGreen(), ball.getColor().getBlue(), 255 - a);
		ball.setColor(fadeOutRed);
	}

	/**
	 *
	 * @param b referência para uma instância de IBall que é usada para passar a velocidade e as coordenadas para a bola duplicada.
	 * @param t referência para uma instância de Target, utilizada para criar a bola duplicada na coordenada do target.
	 */
	private void createDuplicatedBall(IBall b, Target t) {
		double aux[] = randomDouble();
		vetorNormalizado = normalize(aux[0], aux[1]);

		if (ballClass.getName().equals("FxBall")) {
			d_BallList.add(new FxBall(t.getCx(), t.getCy(), b.getWidth(), b.getHeight(), fadeOutRed, defaultSpeed, b.getVx(), vetorNormalizado[0]));
			fadeController.add(new BallManager("FxBall"));
		}
		else {
			d_BallList.add(new Ball(t.getCx(), t.getCy(), b.getWidth(), b.getHeight(), fadeOutRed, defaultSpeed, b.getVx(), vetorNormalizado[1]));
			fadeController.add(new BallManager("Ball"));
		}
		removeDuplicatedBall();
	}

	/**
	 * Método que determina a duração do impulso (maior velocidade) recebido pela(s) bola(s)
	 * @param ball referência da instância IBall, utilizada para definir a nova velocidade da bola, após a colisão com o alvo de impulso de velocidade.
	 */
	private void boostDuration( IBall ball ){
		new java.util.Timer().schedule(new java.util.TimerTask() {
			@Override
			public void run() {
				ball.setSpeed(getDefaultSpeed());
			}
		}, BoostTarget.BOOST_DURATION);
	}

	/**
	 * Método resoponsável por remover a bola duplicada após "DuplicatorTarget.EXTRA_BALL_DURATION" milisegundos.
	 */
	private void removeDuplicatedBall(){
		new java.util.Timer().schedule(new java.util.TimerTask() {
			@Override
			public void run() {
				d_BallList.remove(0);
				fadeController.remove(0);
			}
		}, DuplicatorTarget.EXTRA_BALL_DURATION);
	}

	/**
	 *  Método que gera um número aleatório entre 0,1 e 0,4.
	 *  Método utilizado para atribuir um valor ao novo Vy.
	 */
	private double[] randomDouble() {
		Random r = new Random();
		vetoresXY[0] = (double) ( r.nextInt(90) + 10 );
		vetoresXY[1] = (double) ( r.nextInt(90) + 10 );
 		return vetoresXY;
	}

	/**
	 * Método que guarda a velocidade padrão da bola.
	 */
	private static double getDefaultSpeed() {
		return defaultSpeed;
	}

	/**
	 * Imprime a quantidade de bolas duplicadas.
	 */
	private void ballsQuantity() {
		System.out.println("SIZE: " + d_BallList.size());
	}
}