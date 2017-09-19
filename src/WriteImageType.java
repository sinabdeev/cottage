import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class WriteImageType {
	
	int brickCorner;
	int bottomCorner = 50;

	/**
	 * 
	 * A4 = {595 x 842}
	 * 
	 * 
	 * @throws Exception
	 */
	public void draw(Calc calc) throws Exception {
		try {

			int offset = 0;

			double k = 1; // 2*42/100;

			long 
				width  = Math.round(700 * k) - offset, 
				height = Math.round(600  * k) - offset;

			// TYPE_INT_ARGB specifies the image format: 8-bit RGBA packed
			// into integer pixels
			BufferedImage bi = new BufferedImage((int) width, (int) height,
					BufferedImage.TYPE_INT_ARGB);

			Graphics2D ig2 = bi.createGraphics();

			Font font = new Font("TimesRoman", Font.BOLD, 20);
			ig2.setFont(font);
//			String message = "www.java2s.com!";
//			FontMetrics fontMetrics = ig2.getFontMetrics();
//			int stringWidth = fontMetrics.stringWidth(message);
//			int stringHeight = fontMetrics.getAscent();

			// ig2.drawString(message, (width - stringWidth) / 2, height / 2 +
			// stringHeight / 4);

			ig2.setPaint(Color.white);
			ig2.fillRect(0, 0, (int)width, (int)height);
			
//			ig2.setPaint(Color.black);
//			ig2.drawLine(50, 50, 90, 90);
//
//			ig2.setPaint(Color.blue);
//			ig2.drawLine(60, 0, 195, 195);

			doFazendaDrawing(ig2, calc);

			ImageIO.write(bi, "PNG", new File("d:\\Fazenda\\yourImageName.PNG"));
			ImageIO.write(bi, "JPEG",
					new File("d:\\Fazenda\\yourImageName.JPG"));
			ImageIO.write(bi, "gif", new File("d:\\Fazenda\\yourImageName.GIF"));
			ImageIO.write(bi, "BMP", new File("d:\\Fazenda\\yourImageName.BMP"));

		} catch (IOException ie) {
			ie.printStackTrace();
		}

	}

	void doFazendaDrawing(Graphics2D graphics, Calc calc) {
		graphics.setPaint(Color.ORANGE);
		brickCorner = bottomCorner + (calc.normalOffset)/2;
		graphics.drawRect(brickCorner, brickCorner, calc.northWall-1, calc.westWall-1);
		
		doRoomDrawing(graphics, calc);
		doBathDrawing(graphics, calc);
		doCorridorDrawing(graphics, calc);
		doBottomDrawing(graphics, calc);
	}
	
	int roomCorner;
	
	void doRoomDrawing(Graphics2D graphics, Calc calc) {
		graphics.setPaint(Color.ORANGE);
		
		roomCorner = brickCorner + calc.brickLength-1;
		graphics.drawRect(roomCorner, roomCorner, calc.roomLength+1, calc.roomWidth+1);
	}
	
	int bathCorner;

	void doBathDrawing(Graphics2D graphics, Calc calc) {
		graphics.setPaint(Color.ORANGE);
		bathCorner = roomCorner + calc.roomLength + calc.brickLength;
		graphics.drawRect(bathCorner, roomCorner, calc.bathLength+1, calc.bathWidth+1);
	}
	
	int corridorCorner; 
	
	void doCorridorDrawing(Graphics2D graphics, Calc calc) {
		graphics.setPaint(Color.ORANGE);
		corridorCorner = roomCorner + calc.bathWidth + calc.brickLength;
		graphics.drawRect(bathCorner, corridorCorner, calc.corridorLength+1, calc.corridorWidth+1);
	}
	
	void doBottomDrawing(Graphics2D graphics, Calc calc) {
		
		graphics.setPaint(Color.black);
		graphics.drawRect(bottomCorner, bottomCorner, calc.northWall+calc.normalOffset-1, calc.westWall+calc.normalOffset-1);
		
	}
	
	
	
	
}
