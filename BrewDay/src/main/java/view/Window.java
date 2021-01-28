package view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class Window {

	protected Shell shell;
	protected VerifyListener floatVerifyListener;
	protected Display display;
	
	public Window() {
		floatVerifyListener = new VerifyListener() {

            @Override
            public void verifyText(VerifyEvent e) {
                // allows cut (CTRL + x)
                if (e.text.isEmpty()) {
                    e.doit = true;
                } else if (e.keyCode == SWT.ARROW_LEFT ||
                            e.keyCode == SWT.ARROW_RIGHT ||
                            e.keyCode == SWT.BS ||
                            e.keyCode == SWT.DEL ||
                            e.keyCode == SWT.CTRL ||
                            e.keyCode == SWT.SHIFT) {
                    e.doit = true;
                } else {
                    boolean allow = false;
                    for (int i = 0; i < e.text.length(); i++) {
                        char c = e.text.charAt(i);
                        allow = Character.isDigit(c) || c=='.';
                        if ( ! allow ) {
                            break;
                        }
                    }
                    e.doit = allow;
                }

            }
        };
	}
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Window window = new Window();
			window.open();
		} catch (Exception e) {
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		display = Display.getDefault();
		createContents(new Shell());
		shell.setImage(new Image(display, "resources/icona.png"));
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents(Shell s) {
		shell = s;
	}
	
	public Image imageScale(Image image, int width, int height) {


	    ImageData data = image.getImageData();

	    // Some logic to keep the aspect ratio
	    float img_height = data.height;
	    float img_width = data.width;
	    float container_height = height;
	    float container_width = width;

	    float dest_height_f = container_height;
	    float factor = img_height / dest_height_f;

	    int dest_width = (int) Math.floor(img_width / factor );
	    int dest_height = (int) dest_height_f;

	    if(dest_width > container_width) {
	        dest_width = (int) container_width;
	        factor = img_width / dest_width;
	        dest_height = (int) Math.floor(img_height / factor);

	    }

	    // Image resize
	    data = data.scaledTo(dest_width, dest_height);
	    Image scaled = new Image(Display.getDefault(), data);
	    image.dispose();
	    return scaled;
	}

}
