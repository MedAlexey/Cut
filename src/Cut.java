import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cut {

    @Option(name = "-c", required = false, usage = "Character separation")   //если нет, равен null
    private Boolean charIndention;

    @Option(name = "-w", required = false, usage = "Word separation")
    private Boolean wordIndention;

    @Option(name ="-o", metaVar ="ofile", required = false, usage = "Output file name")
    private String outputFileName;

    @Argument
    private List<String> arguments = new ArrayList<>();

    private char wOrC;
    private String file;
    private int from;
    private int to;

    public static void main(String[] args) throws IOException {
        new Cut().launch(args);
    }



    private void launch(String[] args) throws IOException {
        CmdLineParser parser = new CmdLineParser(this);

        try {
            parser.parseArgument(args);

            if( arguments.isEmpty() || arguments.size() > 2 || arguments.size() < 1) {
                System.out.println("No arguments given");
                System.err.println("java -jar Cut.jar -c|-w -o ofile file range");
                parser.printUsage(System.err);
                return;
            }
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println("java -jar Cut.jar -c|-w -o ofile file range");
            parser.printUsage(System.err);
            return;
        }

        if (charIndention == wordIndention){
            System.err.println("java -jar Cut.jar -c|-w -o ofile file range");
            parser.printUsage(System.err);
            return;
        }

        if (outputFileName == null) outputFileName = "";
        if (charIndention == null) wOrC = 'w';
          else wOrC = 'c';    //почему не могу заменить на это: charIndention == null?wOrC = 'c': wOrC ='w'


        List<String> list = HandlingKt.argParsing(arguments);
        file = list.get(0);
        from = Integer.parseInt(list.get(1));
        to = Integer.parseInt(list.get(2));

        new Cut().reading(wOrC, outputFileName, file, from, to);

    }

    private void reading(char wOrC, String ofile, String file, int from, int to){
        if (file == "") {
            StringBuilder builder = new StringBuilder();

            Scanner keyboard = new Scanner(System.in);        // чтение из консоли, заканчивается, когда вводят пустую строку
            String line = null;
            while(!(line = keyboard.nextLine()).isEmpty()) {
                String result = HandlingKt.lineHandling(wOrC, from, to, line);
                if (!result.isEmpty()) builder.append(result + "\n");
            }
            if (ofile == "") System.out.print(builder.toString());
            else HandlingKt.toFile(ofile, builder.toString());
        }
        else {
            String result = HandlingKt.fromFile(wOrC, file, from, to);
            if (ofile != "") HandlingKt.toFile(ofile, result);
            else System.out.print(result);
        }
    }
}