package main;

public class Main {
private static int oper=-1;
private static FileConverter f = new FileConverter();
	public static void main(String [] args){
		if (args.length > 1) {
			System.out
					.println("Incorrect Number of Arguments: Please enter '1','2' or '3'");
		} else {
			oper = Integer.parseInt(args[0]);
		}

		switch (oper) {
		case 1:
			System.out.println("Mining The Spaces");
			doSpaceMining();
			System.out.println("Done");
			break;
		case 2:
			System.out.println("Noise Cancellation selected");
			System.out.println("Done");
			break;
		case 3:
			System.out.println("Image Enhancement selected");
			System.out.println("Done");
			break;

		default:
			break;
		}
	}

	private static void doSpaceMining(){
		SpaceMining sp = new SpaceMining(f.convert("hubble.tif"));
	}
	
}
