package protect.videotranscoder.activity;

import java.io.File;
import android.content.Context;
import android.media.MediaScannerConnection;
import android.media.MediaScannerConnection.MediaScannerConnectionClient;
import android.net.Uri;

public class SingleMediaScanner implements MediaScannerConnectionClient {

    private MediaScannerConnection mMs;
    private String mFile;
    private String mime;

    public SingleMediaScanner(Context context, String f, String mimeType) {
        mime = mimeType;
        mFile = f;
        mMs = new MediaScannerConnection(context, this);
        mMs.connect();
    }

    @Override
    public void onMediaScannerConnected() {
        if(mime == "image/png") {
            for(File file : (new File(mFile)).listFiles()) {
                mMs.scanFile(file.getAbsolutePath(), mime);
            }
        } else {
            mMs.scanFile(mFile, mime);
        }
    }

    @Override
    public void onScanCompleted(String path, Uri uri) {
        mMs.disconnect();
    }

}