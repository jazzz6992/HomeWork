package l15;

public class Main {
    public static void main(String[] args) {
        ParsingThread parsingThread = new ParsingThread();
        DownloadThread downloadThread = new DownloadThread(parsingThread);
        downloadThread.start();
        parsingThread.start();
    }
}
