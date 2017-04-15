import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CmdLine {

    @Option(name = "-c", required = false, usage = "Character separation")   //если нет, равен null
    private Boolean charIndention;

    @Option(name = "-w", required = false, usage = "Word separation")
    private Boolean wordIndention;

    @Option(name = "-o", metaVar = "ofile", required = false, usage = "Output FileName name")
    private static String outputFileName;

    @Argument
    private List<String> arguments = new ArrayList<>();

    private static char lineSplitting;
    private static String inputFileName;
    private static int startPosition;
    private static int endPosition;


    public void CmdParse(String[] args) throws IOException {
        CmdLineParser parser = new CmdLineParser(this);

        try {
            parser.parseArgument(args);

            if (arguments.isEmpty() || arguments.size() > 2 || arguments.size() < 1) {
                System.out.println("No arguments given");
                System.err.println("java -jar Cut.jar -c|-w -o ofile inputFileName range");
                parser.printUsage(System.err);
                return;
            }
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println("java -jar Cut.jar -c|-w -o ofile inputFileName range");
            parser.printUsage(System.err);
            return;
        }

        if (charIndention == wordIndention) {
            System.err.println("java -jar Cut.jar -c|-w -o ofile inputFileName range");
            parser.printUsage(System.err);
            return;
        }

        if (outputFileName == null) outputFileName = "";

        lineSplitting = charIndention == null ? 'w' : 'c';


        List<String> list = ArgParsingKt.argParsing(arguments);
        inputFileName = list.get(0);
        startPosition = Integer.parseInt(list.get(1));
        endPosition = Integer.parseInt(list.get(2));

    }

    public static int getStartPosition() {
        return (startPosition);
    }

    public static int getEndPosition() {
        return (endPosition);
    }

    public static String getOutputFileName() {
        return (outputFileName);
    }

    public static String getInputFileName() {
        return (inputFileName);
    }

    public static char getLineSplitting() { return (lineSplitting); }

}
