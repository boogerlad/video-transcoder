package protect.videotranscoder.picker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nononsenseapps.filepicker.FilePickerFragment;

import protect.videotranscoder.R;
import java.io.File;

public class FastScrollerFilePickerFragment extends FilePickerFragment {
    // File extension to filter on
    private static final String EXTENSION = ".mp4";

    /**
     *
     * @param file
     * @return The file extension. If file has no extension, it returns null.
     */
    private String getExtension(File file) {
    	if(file == null) {
    		throw new NullPointerException("file is marked @NonNull but is null");
    	}
        String path = file.getPath();
        int i = path.lastIndexOf(".");
        if (i < 0) {
            return null;
        } else {
            return path.substring(i);
        }
    }

    @Override
    protected boolean isItemVisible(final File file) {
        boolean ret = super.isItemVisible(file);
        if (ret && !isDir(file) && (mode == MODE_FILE || mode == MODE_FILE_AND_DIR)) {
            String ext = getExtension(file);
            return ext != null && EXTENSION.equalsIgnoreCase(ext);
        }
        return ret;
    }
    @Override
    protected View inflateRootView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_fastscrollerfilepicker, container, false);
    }
    @Override
    protected int compareFiles(File lhs, File rhs) {
        if (lhs.isDirectory() && !rhs.isDirectory()) {
            return -1;
        } else if (rhs.isDirectory() && !lhs.isDirectory()) {
            return 1;
        } else {
            return rhs.getName().compareToIgnoreCase(lhs.getName());
        }
    }
}