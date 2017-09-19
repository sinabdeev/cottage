
public class Calc {
	
	
	int bottomDepth = 50;
	
	int bottomWidth = 35;
	
	int roomLength = 300;
	int roomWidth = 440;
	
	int bathWidth = 250;
	int bathLength = 200;
	
	int corridorWidth = -1111;
	int corridorLength = -1111;
	
	
	int brickLength = 25;
	int brickWidth = 12;
	
	int armatureFrequency = 100;
	
	/*
	  	wall - стена
	  
		north - север
		south -  юг
		
		east  - восток
		west - запад
		
		living space
		all space
	 */
	
	public static void main(String[] args) {
		Calc calc = new Calc();
		calc.doit();
		WriteImageType image = new WriteImageType();
		try {
			image.draw(calc);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	void log (String s){
		System.out.println(s);	
	}
	
	int northWall  ;
	int westWall ;
	int normalOffset;
	public void doit() {
		
		northWall  = brickLength + roomLength + brickLength + bathLength + brickLength; 
		log("northWall = " + northWall);
		
		westWall = brickLength + roomWidth + brickLength;
		log("westWall = " + westWall);
		
		int housePerimetr = (northWall+westWall)*2;
		
		normalOffset = bottomWidth-brickLength;
		int smallOffset = bottomWidth-brickLength;
		
		int outerNorthWall = northWall + normalOffset;
		int outerWestWall = westWall + normalOffset;
		int allSpace = (outerNorthWall) * (outerWestWall);
		int outerPerimetr = (outerNorthWall + outerWestWall) * 2;
		log("allSpace = " + allSpace);
		log("outerPerimetr = " + outerPerimetr);
		
		corridorLength = bathLength;
		corridorWidth = roomWidth - brickLength - bathWidth;
		
		log("corridorWidth = " + corridorWidth);
		//--------------------------------------------------------------
		int roomInnerLength = roomLength - normalOffset;
		int roomInnerWidth = roomWidth - normalOffset;
		int roomInnerPerimeter = (roomInnerLength + roomInnerWidth) * 2;
		int roomPerimeter = (roomLength + roomWidth) * 2;
		log("roomInnerPerimeter = " + roomInnerPerimeter);
		int roomSpace = (roomInnerLength) * (roomInnerWidth);
		log("roomSpace = " + roomSpace);
		
		int bathInnerLength = bathLength - normalOffset;
		int bathInnerWidth = bathWidth - smallOffset/2 - normalOffset/2;
		int bathInnerPerimeter = (bathInnerLength + bathInnerWidth) * 2;
		int bathPerimeter = (bathLength + bathWidth) * 2;
		log("bathInnerPerimeter = " + bathInnerPerimeter);
		int bathSpace = (bathInnerLength)*(bathInnerWidth);
		log("bathSpace = " + bathSpace);
		
		int corridorInnerLength = corridorLength - normalOffset ;
		int corridorInnerWidth = corridorWidth - smallOffset/2 - normalOffset/2;
		int corridorInnerPerimeter = (corridorInnerLength + corridorInnerWidth) * 2;
		int corridorPerimeter = (corridorLength + corridorWidth) * 2;
		log("corridorInnerPerimeter = " + corridorInnerPerimeter);
		int corridorSpace = (corridorInnerLength)*(corridorInnerWidth);
		log("corridorSpace = " + corridorSpace + " [centimeter2]");
		//--------------------------------------------------------------------
		int bottomSpace = allSpace - roomSpace - bathSpace - corridorSpace;
		log("bottomSpace = " + bottomSpace + " [centimeter2]");
		
		int bottomVolume = bottomSpace * bottomDepth;
		log("bottomVolume = " + bottomVolume + " [centimeter3]");
		
		int bottomPerimetr = outerPerimetr + roomInnerPerimeter + bathInnerPerimeter + corridorInnerPerimeter;
		log("bottomPerimetr = " + bottomPerimetr + " [centimeter]");
		
		int horizont_longitudinal_Armature = 2 * ( roomPerimeter + bathPerimeter + corridorPerimeter + housePerimetr ); // продольный
		double armature_circle_count = (housePerimetr + roomWidth + bathLength)/armatureFrequency; // поперечный
		log("armature_circle_count = " + armature_circle_count + " [count]");
		double armature_circle = armature_circle_count * (brickLength + bottomWidth) * 2;
		long armature = horizont_longitudinal_Armature + Math.round(armature_circle);
		log("armature = " + armature + " [centimeter]");
		
		calcBricks1();
		calcBricks2();
	}
	
	void calcBricks1 () {
	    int roomHeight = 250;
	    int pedestalHeight = 20; // цоколь
	    int rastvorHeight = 1;
	    double brickHeight10 = 6.5; 
	    double brickHeight15 = 8.8;
	    	
	    log("----------------------------------------1--------------------------------------------------------");
	    
	    int houseSpace = northWall * westWall ;
	    log("houseSpace = " + houseSpace);
	    
	    int roomSpace = roomLength * roomWidth ;
	    log("roomSpace = " + roomSpace);
	    
	    int bathSpace = bathLength * bathWidth ;
	    log("bathSpace = " + bathSpace);
	    
	    int corridorSpace = corridorLength * corridorWidth ;
	    log("corridorSpace = " + corridorSpace);
	    
	    
	    int bricksSpace = houseSpace - roomSpace - bathSpace - corridorSpace ;
	    log("bricksSpace = " + bricksSpace);
	    
	    double brickSpace = (brickLength + 0.5) * (brickWidth + 0.5) ;
	    log("brickSpace = " + brickSpace + "   (1)");
	    
	    
	    double bricksInLayerNumber = bricksSpace / brickSpace ;
	    log("bricksInLayerNumber = " + bricksInLayerNumber);
	    
	    double bricksLayerCount10 = 3;
	    log("bricksLayerCount10 = " + bricksLayerCount10);
	    double bricksCount10 = bricksInLayerNumber * bricksLayerCount10;
	    log("bricksCount10 = " + bricksCount10);
	    
	    double bricksLayerCount15 = roomHeight / (brickHeight15 + rastvorHeight);
	    log("bricksLayerCount15 = " + bricksLayerCount15);
	    double bricksCount15 = bricksInLayerNumber * bricksLayerCount15;
	    log("bricksCount15 = " + bricksCount15);	    
	    
	}
	
	void calcBricks2 () {
	    log("----------------------------------------2---A-----------------------------------------------------");
	    int roomHeight = 130;
	    int rastvorHeight = 1;
	    double brickHeight10 = 6.5; 
	    double brickHeight15 = 8.8;
	    
	    int secondFloorSpace = (roomLength + 2 * brickLength) * (roomWidth + 2 * brickLength);
	    int roomSpace = roomLength * roomWidth ;
	    int bricksSpace = secondFloorSpace - roomSpace;
	    log("bricksSpace = " + bricksSpace);
	    
	    double brickSpace = (brickLength + 0.5) * (brickWidth + 0.5) ;
	    log("brickSpace = " + brickSpace + "   (2)");
	    

	    double bricksInLayerNumber = bricksSpace / brickSpace ;
	    log("bricksInLayerNumber = " + bricksInLayerNumber);
	    
	    double bricksLayerCount15 = roomHeight / (brickHeight15 + rastvorHeight);
	    log("bricksLayerCount15 = " + bricksLayerCount15);
	    double bricksCount15 = bricksInLayerNumber * bricksLayerCount15;
	    log("bricksCount15 = " + bricksCount15);	    
	    
	    
	    frontons () ;
	}
	
	void frontons () {
	    
	    log("-------------------------------------frontons---------------------------------------------");	    
	    
	    int secondFloorLength = roomLength + 2 * brickLength;
	    int roomHeight = 150;
	    
	    double bricksSpace = roomHeight * secondFloorLength/2;
	    log("bricksSpace = " + bricksSpace);
	    
	    double brickSpace = (brickLength + 0.5) * (brickWidth + 0.5) ;
	    log("brickSpace = " + brickSpace + "   (2)");
	    
	    double bricksInLayerNumber = bricksSpace / brickSpace ;
	    log("bricksInLayerNumber = " + bricksInLayerNumber);
	    
	    double bricksLayerCount15 = 4;
	    double bricksCount15 = bricksInLayerNumber * bricksLayerCount15;
	    log("bricksCount15 = " + bricksCount15);	    
	    
	}

}
