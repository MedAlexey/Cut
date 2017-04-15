import kotlin.reflect.jvm.internal.impl.descriptors.EffectiveVisibility;
import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Cut {

    public static void main(String[] args) throws Exception {
        new CmdLine().CmdParse(args);
        try{
            new Cut().reading(CmdLine.getOutputFileName(),
                    CmdLine.getInputFileName());
        }
        catch(Exception e){

        }
    }



    private void reading(String ofile, String file) throws Exception{
        InputStream inputStream;
        OutputStream outputStream;
        if (ofile != "") outputStream = new FileOutputStream(ofile);
        else outputStream = new BufferedOutputStream(System.out);

        if (file == "") {
            StringBuilder builder = new StringBuilder();

            inputStream = new BufferedInputStream(System.in);
            int data = inputStream.read();
            char content;
            while(data != -1) {
                content = (char) data;
                builder.append(content);
                if (content == '\n'){
                    printing(outputStream,builder);
                    builder.delete(0,builder.length());
                }

                data = inputStream.read();
                if(data == -1){              //если конец файла
                    printing(outputStream,builder);
                }

            }
        } else {
            try{
                StringBuilder result = new StringBuilder();
                inputStream = new FileInputStream(file);
                int data = inputStream.read();               //читаем 1ый символ
                char content;
                while(data != -1){               //читаем файл посимвольно
                    content = (char) data;   //переводим символ в чар
                    result.append(content);
                    if (content == '\n'){         //если конец строки
                        printing(outputStream,result);
                        result.delete(0,result.length());
                    }

                    data = inputStream.read();  //читаем следующий символ
                    if(data == -1){              //если конец файла
                        printing(outputStream,result);
                    }
                }
            }
            catch (FileNotFoundException e){
                return;
            }
        }
        outputStream.close();
        inputStream.close();
    }

    private void printing(OutputStream outputStream, StringBuilder result){
        String line = processedLine(result.toString());
        line += '\n';
        try{
            for (int i=0; i< line.length(); i++){
                int byt = (byte)line.charAt(i);
                outputStream.write(byt);
            }
        }catch (IOException e){
            return;
        }
    }

    private String processedLine(String line){
         return CmdLine.getLineSplitting() == 'w' ?
                LineHandlingKt.wordHandling(CmdLine.getStartPosition(), CmdLine.getEndPosition(), line) :
                LineHandlingKt.charHandling(CmdLine.getStartPosition(), CmdLine.getEndPosition(), line);
    }


}