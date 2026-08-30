package br.com.avsouza7.service;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Service;

import br.com.avsouza7.model.ParaMontarImagem;

@Service
public class ImagemConcursoUseCase {

	private static final int PADDING = 50;
	private static final int OVAL_SIZE = 40;
	private static final int ESPACO_ENTRE_DEZENAS = 45;
	private static final int ESPACO_ENTRE_LINHAS = 30;
	private static final Font TITULO_FONT = new Font("Arial", Font.BOLD, 32);
	private static final Font TEXTO_FONT = new Font("Arial", Font.PLAIN, 20);

	public void imprimir(ParaMontarImagem para) throws IOException {
		// Calcular largura baseada no maior texto
		String textoReferencia = String.format("Acumulado sorteio especial: %s %s", para.getSorteio().getAcumuladoSorteioEspecial(), para.getSorteio().getValorAcumuladoConcursoEspecialFormatado());

		BufferedImage dummy = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
		Graphics2D gDummy = dummy.createGraphics();
		gDummy.setFont(TEXTO_FONT);
		FontMetrics fm = gDummy.getFontMetrics();
		int larguraTexto = fm.stringWidth(textoReferencia);
		gDummy.dispose();

		int largura = Math.max(800, larguraTexto + 2 * PADDING);
		int altura = 1600; // altura fixa, pode ser calculada dinamicamente também

		BufferedImage image = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = image.createGraphics();

		int x = PADDING;
		int y = PADDING;

		// Fundo branco
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, largura, altura);

		// Título
		g.setColor(Color.BLACK);
		g.setFont(TITULO_FONT);
		g.drawString(String.format("🟦 Resultado concurso %s %s", para.getIdLoteria(), para.getLoteriaEnum().getNome()), x, y);
		y += ESPACO_ENTRE_LINHAS + 20;

		// Dezenas sorteadas
		g.setFont(TEXTO_FONT);
		g.drawString("Dezenas sorteadas:", x, y);
		y += ESPACO_ENTRE_LINHAS;

		for (var d : para.getSorteio().getDezenas()) {
			g.setColor(Color.BLUE);
			g.fillOval(x, y, OVAL_SIZE, OVAL_SIZE);
			g.setColor(Color.WHITE);
			String dezena = String.valueOf(d.getNuDezena());
			FontMetrics metrics = g.getFontMetrics();
			int textWidth = metrics.stringWidth(dezena);
			int textHeight = metrics.getAscent();
			g.drawString(dezena, x + (OVAL_SIZE - textWidth) / 2, y + ((OVAL_SIZE + textHeight) / 2) - 2);
			x += ESPACO_ENTRE_DEZENAS;
		}

		y += OVAL_SIZE + ESPACO_ENTRE_LINHAS;
		x = PADDING;
		y = imprimeInformacoesSorteio(para, g, x, y);
		y += ESPACO_ENTRE_LINHAS;

		// Resultado das apostas
		g.drawString("Suas apostas:", x, y);
		y += ESPACO_ENTRE_LINHAS;

		g.drawString(String.format("Valor do seu prêmio: %s", para.getValorDoPremio()), x, y);
		y += ESPACO_ENTRE_LINHAS;

		imprimeApostas(para, g, y);

		g.dispose();

		// Salvar imagem
		ImageIO.write(image, "png", new File("resultado_concurso.png"));
	}

	private int imprimeInformacoesSorteio(ParaMontarImagem para, Graphics2D g, int x, int y) {
		// Informações do sorteio
		g.setColor(Color.BLACK);
		g.drawString(String.format("Data do sorteio: %s", para.getSorteio().getDtSorteioFormatado()), x, y);
		y += ESPACO_ENTRE_LINHAS;

		g.drawString(String.format("Acumulado próximo concurso: %s", para.getSorteio().getValorAcumuladoFormatado()), x, y);
		y += ESPACO_ENTRE_LINHAS;

		g.drawString(String.format("Próximo sorteio: %s", para.getSorteio().getDtProximoSorteioFormatado()), x, y);
		y += ESPACO_ENTRE_LINHAS;

		g.drawString(String.format("Acumulado sorteio especial: %s %s", para.getSorteio().getAcumuladoSorteioEspecial(), para.getSorteio().getValorAcumuladoConcursoEspecialFormatado()), x, y);
		return y;
	}

	private void imprimeApostas(ParaMontarImagem para, Graphics2D g, int y) {
		int x;
		for (var r : para.getResultados()) {
			x = PADDING;
			for (var d : r.getDezenas()) {
				g.setColor(d.isFoiSorteada() ? Color.decode("#7CFC00") : Color.decode("#911687"));
				g.fillOval(x, y, OVAL_SIZE, OVAL_SIZE);
				g.setColor(Color.WHITE);
				String dezena = String.valueOf(d.getNuDezena());
				FontMetrics metrics = g.getFontMetrics();
				int textWidth = metrics.stringWidth(dezena);
				int textHeight = metrics.getAscent();
				g.drawString(dezena, x + (OVAL_SIZE - textWidth) / 2, y + ((OVAL_SIZE + textHeight) / 2) - 2);
				x += ESPACO_ENTRE_DEZENAS;
			}
			g.setColor(Color.BLUE);
			g.fillOval(x, y, OVAL_SIZE, OVAL_SIZE);
			g.setColor(Color.WHITE);
			g.drawString(String.valueOf(r.getNuAcertos()), x + 12, y + 27);
			y += OVAL_SIZE + ESPACO_ENTRE_LINHAS;
		}
	}
}
