import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


/**
 * Framework to test the multiset implementations.
 * 
 * @author jkcchan
 */
public class MultisetTesterCopy
{
	/** Name of class, used in error messages. */
	protected static final String progName = "MultisetTester";
	
	/** Standard outstream. */
	protected static final PrintStream outStream = System.out;
	
	protected static final int RANGE = 8000;
	
	protected static final int ITEMS = 10000;

	protected static final int OPERATIONS = 5000;
	
	protected static final boolean REFRESH_OPERATIONS = false;
	
	protected static final String OPERATION_FILE = "operation1.in";
	
	
	protected static final String[] OPS = {"A", "S", "RO", "RA"};
	
	protected static final double RATIO_A = 0.2;
	protected static final double RATIO_S = 0.6;
	protected static final double RATIO_RO = 0.2;
	protected static final double RATIO_RA = 0.0;
	
    protected static DataGenerator generator = new DataGenerator(1, RANGE);
	
	static long time_of_add = 0;
	static long time_of_search = 0;
	static long time_of_remove_one = 0;
	static long time_of_remove_all = 0;

	/**
	 * Print help/usage message.
	 */
	public static void usage(String progName) {
		System.err.println(progName + ": <implementation> [fileName to output search results to]");
		System.err.println("<implementation> = <linkedlist | sortedlinkedlist | bst| hash | baltree>");
		System.exit(1);
	} // end of usage


	/**
	 * Process the operation commands coming from inReader, and updates the multiset according to the operations.
	 * 
	 * @param inReader Input reader where the operation commands are coming from.
	 * @param searchOutWriter Where to output the results of search.
	 * @param multiset The multiset which the operations are executed on.
	 * 
	 * @throws IOException If there is an exception to do with I/O.
	 */
	public static void processOperations(BufferedReader inReader, PrintWriter searchOutWriter, Multiset<String> multiset) 
		throws IOException
	{
		String line;
		int lineNum = 1;
		boolean bQuit = false;
		
		// continue reading in commands until we either receive the quit signal or there are no more input commands
		while (!bQuit && (line = inReader.readLine()) != null) {
			String[] tokens = line.split(" ");

			// check if there is at least an operation command
			if (tokens.length < 1) {
				System.err.println(lineNum + ": not enough tokens.");
				lineNum++;
				continue;
			}

			String command = tokens[0];
			// determine which operation to execute
			switch (command.toUpperCase()) {
				// add
				case "A":
					if (tokens.length == 2) {
						long startTime = System.nanoTime();
						multiset.add(tokens[1]);
						time_of_add += System.nanoTime() - startTime;
					}
					else {
						System.err.println(lineNum + ": not enough tokens.");
					}
					break;
				// search
				case "S":
					if (tokens.length == 2) {
						long startTime = System.nanoTime();
						int foundNumber = multiset.search(tokens[1]);
						time_of_search += System.nanoTime() - startTime;
						searchOutWriter.println(tokens[1] + " " + foundNumber);
					}
					else {
						// we print -1 to indicate error for automated testing
						searchOutWriter.println(-1);
						System.err.println(lineNum + ": not enough tokens.");
					}
					break;
				// remove one instance
				case "RO":
					if (tokens.length == 2) {
						long startTime = System.nanoTime();
						multiset.removeOne(tokens[1]);
						time_of_remove_one =+ System.nanoTime() - startTime;
					}
					else {
						System.err.println(lineNum + ": not enough tokens.");
					}
					break;
				// remove all instances
				case "RA":
					if (tokens.length == 2) {
						long startTime = System.nanoTime();
						multiset.removeAll(tokens[1]);
						time_of_remove_all =+ System.nanoTime() - startTime;
					}
					else {
						System.err.println(lineNum + ": not enough tokens.");
					}
					break;		
				// print
				case "P":
					multiset.print(outStream);
					break;
				// quit
				case "Q":
					bQuit = true;
					break;
				default:
					System.err.println(lineNum + ": Unknown command.");
			}

			lineNum++;
		}

	} // end of processOperations() 


	/**
	 * Main method.  Determines which implementation to test.
	 */
	public static void main(String[] args) {
		// check number of command line arguments
		if (args.length > 2 || args.length < 1) {
			System.err.println("Incorrect number of arguments.");
			usage(progName);
		}

		String implementationType = args[0];
		
		String searchOutFilename = null;
		if (args.length == 2) {
			searchOutFilename = args[1];
		}
		
		// determine which implementation to test
		Multiset<String> multiset = null;
		switch(implementationType) {
			case "linkedlist":
				multiset = new LinkedListMultiset<String>();
				break;
			case "sortedlinkedlist":
				multiset = new SortedLinkedListMultiset<String>();
				break;
			case "bst":
				multiset = new BstMultiset<String>();
				break;
			case "hash":
				multiset = new HashMultiset<String>();
				break;
			case "baltree":
				multiset = new BalTreeMultiset<String>();
				break;
			default:
				System.err.println("Unknown implmementation type.");
				usage(progName);
		}

		generateData(multiset);
		
		generateOperations();
		// construct in and output streams/writers/readers, then process each operation.
		try {
			BufferedReader inReader = new BufferedReader(new FileReader(OPERATION_FILE));
			PrintWriter searchOutWriter = new PrintWriter(System.out, true);
			
			if (searchOutFilename != null) {
				searchOutWriter = new PrintWriter(new FileWriter(searchOutFilename), true);
			}
			// process the operations
			processOperations(inReader, searchOutWriter, multiset);
			printTime(implementationType, outStream);
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}

	} // end of main()


	private static void generateOperations() {
		
		if(!REFRESH_OPERATIONS) return ;
		
		Random random = new Random();
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(OPERATION_FILE))) {
			
			Map<String, Integer> ratio = new HashMap<String, Integer>();
			
			ratio.put(OPS[0], (int)(RATIO_A * OPERATIONS));
			ratio.put(OPS[1], (int)(RATIO_S * OPERATIONS));
			ratio.put(OPS[2], (int)(RATIO_RO * OPERATIONS));
			ratio.put(OPS[3], (int)(RATIO_RA * OPERATIONS));

			Map<String, Integer> counter = new HashMap<String, Integer>();
			counter.put(OPS[0], 0);
			counter.put(OPS[1], 0);
			counter.put(OPS[2], 0);
			counter.put(OPS[3], 0);
			
			String line = "";
			int generated = 0;
			while(generated<OPERATIONS) {
				int pos = random.nextInt(4);
				String op = OPS[pos];
				
				// check op availble
				if(counter.get(op)+1<=ratio.get(op)) {
					counter.put(op, counter.get(op)+1);
					generated++;
					int item = random.nextInt(RANGE)+1;
					line = op + " " + item;
					writer.write(line);
					writer.newLine();
				}
			}
//			writer.write("P");
//			writer.newLine();
			writer.write("Q");
			writer.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}


	private static void generateData(Multiset<String> multiset) {
		int[] samples = generator.sampleWithReplacement(ITEMS);
		for(int i=0;i<samples.length;i++) {
			multiset.add(String.valueOf(samples[i]));
		}
	}


	private static void printTime(String implType, PrintStream out) {

		String logFile = "log.out";

		StringBuffer sbuff = new StringBuffer("");
		sbuff.append("=======================================\n");
		sbuff.append("Implementation: 	" + implType + "\n");
		sbuff.append("Number of items: 	" + ITEMS + "\n");
		sbuff.append("Range of items:		" + RANGE + "\n");
		sbuff.append("Number of operations: 	" + OPERATIONS + "\n");
		String ratios = "A - " + RATIO_A * 100 + "%, ";
		ratios += "S - " + RATIO_S * 100 + "%, ";
		ratios += "RO - " + RATIO_RO * 100 + "%, ";
		ratios += "RA - " + RATIO_RA * 100 + "%";
		sbuff.append("Ratio of operations:	" + ratios + "\n");
		sbuff.append("\n");
		sbuff.append("time of addition: 	" + ((double)time_of_add / (1000 * 1000)) + " milliseconds\n");
		sbuff.append("time of search: 	" + ((double)time_of_search / (1000 * 1000)) + " milliseconds\n");
		sbuff.append("time of remove_one:	" + ((double)time_of_remove_one / (1000 * 1000)) + " milliseconds\n");
		sbuff.append("time of remove_all:	" + ((double)time_of_remove_all / (1000 * 1000)) + " milliseconds\n");
		sbuff.append("=======================================\n");
		sbuff.append("\n");
		
		String content = sbuff.toString();
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFile, true))) {
			writer.write(content);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		out.print(content);
	}

} // end of class MultisetTester
