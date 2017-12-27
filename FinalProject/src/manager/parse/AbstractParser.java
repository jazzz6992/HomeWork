package manager.parse;

import manager.listeners.ParseCompleteListener;

import java.io.File;

public abstract class AbstractParser implements Runnable {
    private ParseCompleteListener listener;
    private File file;

    public AbstractParser(ParseCompleteListener listener, File file) {
        this.listener = listener;
        this.file = file;
    }

    abstract void parse(File sourse);

    public ParseCompleteListener getListener() {
        return listener;
    }

    public File getFile() {
        return file;
    }
}
