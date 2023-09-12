import java.io.*;

public class ArchiveReader {
    public InputStream is;

    public ArchiveReader(String arqName) {
        try {
            this.is = new FileInputStream(arqName);
        }catch(FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public int readNextChar() {
        int c = -1;

        try{
            c = is.read();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return c;
    }
}