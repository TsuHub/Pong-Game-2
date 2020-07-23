/*===========*
*			 *
*	TsuHub   *
*			 *
=============*/

import java.awt.*;
import java.util.ArrayList;

public class FxBall extends Ball implements IBall
{
    /**
     * Variável de instância que recebe a cor da bola colidida com o DuplicatorTarget para que esta
     * cor seja atribuída ao rastro. A cor da bola recebida é em RGB, mais o fator alpha, i.e.,
     * esta variável recebe como argumento, um red, green, blue da bola colidida com o
     * DuplicatorTarget e um alpha são adicionados a este atributo.
     */
    private Color fx_color;

    /**
     * ArrayList responsável por guardar uma quantidade "amount" (variável de instância especificado abaixo)
     * de bolas. Cada uma destas bolas recebe a coordenada da bola, assim, quando a bola principal ou duplicada
     * colide com algum objeto (parede ou jogador) cada bola de rastro passa um efeito de colisão também.
     */
    private ArrayList<FxBall> fxball = new ArrayList<FxBall>();

    /**
     * Variável de instância responsável pelo tamanho da próxima bola, i.e., cada uma das próximas bolas
     * de rastro, recebe o tamanho anterior menos o "decreaseFactor". Fazendo a bola ter um rastro com
     * forma "triangular".
     */
    private double decreaseFactor = 0.2;

    /**
     * Variável de instância inteira utilizada como índice para basear o tamanho e coordenada das bolas
     * posteriores em relação a anterior.
     */
    private int index;

    /**
     * Variável inteira que delimita a quantidade de bolas para a composição do rastro.
     */
    private final int amount = 80;

    /**
     * Construtor da Classe FxBall.
     *
     * Faz a chamada do construtor da classe mãe (Ball).
     *
     * @param cx coordenada do eixo x da bola.
     * @param cy coordenada do eixo y da bola.
     * @param width largura da bola.
     * @param height altura da bola.
     * @param color cor da bola.
     * @param speed velocidade da bola.
     * @param vx vetor x da bola.
     * @param vy vetor y da bola.
     */
    public FxBall(double cx, double cy, double width, double height, java.awt.Color color, double speed, double vx, double vy) {
        super(cx, cy, width, height, color, speed, vx, vy);
        fx_color = new Color(color.getRed(), color.getGreen(), color.getBlue(), 10);
    }

    /**
     * Método que cria o rastro da bola de acordo com a cor da bola colidida com o DuplicatorTarget.
     *
     * É adicionado uma nova referência de bola, que é a primeira bola do rastro, caso esta lista
     * de bolas esteja vazia. Esta bola recebe as coordenadas da bola principal.
     * Posteriormente, as outras bolas do rastro são adicionadas, sempre recebendo as coordendas
     * da bola anterior, caso a primeira bola do rastro tenha sido definida.
     *
     * Um laço for é responsável por atualizar cada uma das bolas do rastro de "trás pra frente",
     * i.e., do último para o segundo (já que a primeira bola do rastro recebe as coordenadas
     * da bola principal).
     *
     * Por fim, se a lista não estiver vazia, ocorre a atualização da primeira bola do rastro.
     *
     * @param color
     */
    private void effectTrail(Color color)
    {
        if ( fxball.size() >= 1 ) {
            if ( index < amount ) {
                fxball.add(new FxBall(fxball.get(index).getCx(), fxball.get(index).getCy(), fxball.get(index).getWidth(), fxball.get(index).getHeight(), color, fxball.get(index).getSpeed(), fxball.get(index).getVx(), fxball.get(index).getVy()));
                index++;
            }
        }

        for ( int i = index; i > 0; i-- ) {
            fxball.set(i, new FxBall(fxball.get(i-1).getCx(), fxball.get(i-1).getCy(), fxball.get(i-1).getWidth() - decreaseFactor, fxball.get(i-1).getHeight() - decreaseFactor, fx_color, fxball.get(i-1).getSpeed(), fxball.get(i-1).getVx(), fxball.get(i-1).getVy()));
        }

        if (fxball.isEmpty()) fxball.add(new FxBall(getCx(), getCy(), getWidth(), getHeight(), fx_color, getSpeed(), getVx(), getVy()));
        else fxball.set(0, new FxBall(getCx(), getCy(), getWidth(), getHeight(), fx_color, getSpeed(), getVx(), getVy()));
    }

    /**
     * Método que (re)desenha as bolas do rastro. Sobreescreve o método da classe mãe
     * para que as bolas do rastro sejam (re)desenhadas.
     */
    @Override
    public void draw() {
        super.draw();
        for ( FxBall fb : fxball ) {
            fb.draw();
        }
    }

    /**
     * Sobreescreve o método mãe, para atualizar a posição das bolas do rastro, de acordo
     * com o fx_color da bola.
     * @param d quantidade de milisegundos que se passou entre o ciclo anterior de atualização do jogo e o atual (definição da documentação).
     */
    @Override
    public void update(long d) {
        super.update(d);
        effectTrail(fx_color);
    }

    /**
     * Retorno do método da classe mãe.
     */
    public boolean checkCollision(Wall w) {
        return super.checkCollision(w);
    }

    /**
     * Retorno do método da classe mãe.
     */
    public boolean checkCollision(Player p) {
        return super.checkCollision(p);
    }

    /**
     * Retorno do método da classe mãe.
     */
    public boolean checkCollision(Target t) {
        return super.checkCollision(t);
    }
}